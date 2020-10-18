package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.Constant;
import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.*;
import com.um.cloudfixum.cloudfixum.repository.BudgetRepository;
import com.um.cloudfixum.cloudfixum.repository.MinorJobRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService extends GenericServiceImpl<Budget> {

    private final BudgetRepository budgetRepository;
    private final MinorJobRepository minorJobRepository;
    private final EmailService emailService;

    public BudgetService(BudgetRepository budgetRepository, MinorJobRepository minorJobRepository, EmailService emailService) {
        this.budgetRepository = budgetRepository;
        this.minorJobRepository = minorJobRepository;
        this.emailService = emailService;
    }

    @Override
    public JpaRepository<Budget, Long> getRepository() {
        return budgetRepository;
    }

    public ResponseEntity<List<Budget>> getBudgetsbyCommonUserMail(String email) {
        List<Budget> user_budgets = budgetRepository.findByuserEmail(email);
        return user_budgets.size() == 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(user_budgets, HttpStatus.OK);
    }

    public ResponseEntity<?> create(BudgetRequest budgetRequest) {
        List<Budget> userBudgets = budgetRepository.findByuserEmail(budgetRequest.getUserEmail());
        if (budgetRequest.getMinorJobId() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<MinorJob> minorJob = minorJobRepository.findById(budgetRequest.getMinorJobId());
        if (!minorJob.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        for (Budget budget : userBudgets) {
            if (budget.getMinorJob().getId().equals(budgetRequest.getMinorJobId()) && budget.getBudgetStatus().equals(BudgetStatus.BUDGET_ON_HOLD)) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        }
        Budget budget = new Budget(budgetRequest);
        budget.setMinorJob(minorJob.get());
        emailService.sendEmail(Constant.HAS_A_BUDGET_REQUEST + minorJob.get().getTitle(), minorJob.get().getServiceProvider().getEmail(), Constant.BUDGET_REQUEST);
        emailService.sendEmail(Constant.BUDGET_REQUEST_SUBMITTED, budget.getUserEmail(), Constant.BUDGET_REQUEST);

        return super.create(budget);

    }


    public ResponseEntity<?> answerBudget(BudgetResponse budgetResponse) {
        if (budgetResponse.getPrice() == null || budgetResponse.getBudgetId() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Budget> budget = getRepository().findById(budgetResponse.getBudgetId());

        if (!budget.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!budget.get().getBudgetStatus().equals(BudgetStatus.BUDGET_ON_HOLD))
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        budget.get().setBudgetStatus(BudgetStatus.RESPONSED_BUDGET);
        budget.get().setBudgetPrice(budgetResponse.getPrice());
        budget.get().setProviderResponse(budgetResponse.getProviderResponse());

        emailService.sendEmail(Constant.RESPONSE_TO_THE_BUDGET + budget.get().getMinorJob().getTitle(), budget.get().getUserEmail(), Constant.BUDGET_RESPONSE);

        return super.update(budget.get());
    }

    public ResponseEntity<?> confirmBudget(Long budgetId, boolean budgetConfirmation){
        Optional<Budget> budget = getRepository().findById(budgetId);
        if (!budget.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!budget.get().getBudgetStatus().equals(BudgetStatus.RESPONSED_BUDGET))
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        if (budgetConfirmation) {
            budget.get().setBudgetStatus(BudgetStatus.BUDGET_ACCEPTED);
            emailService.sendEmail(Constant.BUDGET_CONFIRM_SUBMITTED,budget.get().getUserEmail(), Constant.BUDGET_REQUEST);
        } else {
            budget.get().setBudgetStatus(BudgetStatus.BUDGET_REJECTED);
        }

        emailService.sendEmail(Constant.CALLED_SERVICE+budget.get().getMinorJob().getTitle()+Constant.HAS_BEEN+budget.get().getBudgetStatus().getStatus()+Constant.BY_THE_USER, budget.get().getMinorJob().getServiceProvider().getEmail(), Constant.BUDGET_REQUEST);

        return super.update(budget.get());
    }
}
