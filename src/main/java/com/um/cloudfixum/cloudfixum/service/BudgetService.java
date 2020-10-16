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


    public ResponseEntity<HttpStatus> create(BudgetRequest budgetRequest) {
        List<Budget> userBudgets = budgetRepository.findByuserEmail(budgetRequest.getUserEmail());
        if(budgetRequest.getMinorJobId() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<MinorJob> minorJob = minorJobRepository.findById(budgetRequest.getMinorJobId());
        if (!minorJob.isPresent()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        for (Budget budget: userBudgets) {
            if (budget.getMinorJob().getId().equals(budgetRequest.getMinorJobId()) &&  budget.getBudgetStatus().equals(BudgetStatus.BUDGET_ON_HOLD)){
               return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

        }
        Budget budget = new Budget(budgetRequest);
        budget.setBudgetStatus(BudgetStatus.BUDGET_ON_HOLD);
        budget.setMinorJob(minorJob.get());
        super.create(budget);

        emailService.sendEmail( Constant.HAS_A_BUDGET_REQUEST+minorJobRepository.findById(budget.getMinorJob().getId()).get().getTitle(), minorJobRepository.findById(budget.getMinorJob().getId()).get().getServiceProvider().getEmail(), Constant.BUDGET_REQUEST);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }




    public ResponseEntity<List<Budget>> getBudgetsbyCommonUserMail(String email){
        List<Budget> user_budgets = budgetRepository.findByuserEmail(email);
        return user_budgets.size() == 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(user_budgets, HttpStatus.OK);
    }
    public ResponseEntity<Budget> requestBudget(Budget budget){
        String email_content = "";
        if (budget.getImageHash() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //Ver si es opcional.
        if (budget.getBudgetStatus().equals(BudgetStatus.BUDGET_ACCEPTED) || budget.getBudgetStatus().equals(BudgetStatus.BUDGET_REJECTED)){
            email_content = Constant.CALLED_SERVICE+minorJobRepository.findById(budget.getMinorJob().getId()).get().getTitle()+Constant.HAS_BEEN+budget.getBudgetStatus().getStatus()+Constant.BY_THE_USER;
            emailService.sendEmail(email_content, minorJobRepository.findById(budget.getMinorJob().getId()).get().getServiceProvider().getEmail(), Constant.BUDGET_REQUEST);
            return update(budget);
        }else {
            budget.setBudgetStatus(BudgetStatus.BUDGET_ON_HOLD);
            email_content = Constant.HAS_A_BUDGET_REQUEST+minorJobRepository.findById(budget.getMinorJob().getId()).get().getTitle();
            emailService.sendEmail(email_content, minorJobRepository.findById(budget.getMinorJob().getId()).get().getServiceProvider().getEmail(), Constant.BUDGET_REQUEST);
            return create(budget);
        }
    }

    public ResponseEntity <HttpStatus> answerBudget (BudgetResponse budgetResponse) {
        if (budgetResponse.getPrice() == null || budgetResponse.getBudgetId() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<Budget> budget = getRepository().findById(budgetResponse.getBudgetId());

        if (!budget.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!budget.get().getBudgetStatus().equals(BudgetStatus.BUDGET_ON_HOLD)) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        budget.get().setBudgetStatus(BudgetStatus.RESPONSED_BUDGET);
        budget.get().setBudgetPrice(budgetResponse.getPrice());
        budget.get().setProviderResponse(budgetResponse.getProviderResponse());
        super.update(budget.get());
        emailService.sendEmail(Constant.RESPONSE_TO_THE_BUDGET+minorJobRepository.findById(budget.get().getMinorJob().getId()).get().getTitle(), budget.get().getUserEmail(), Constant.BUDGET_RESPONSE);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
