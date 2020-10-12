package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.Constant;
import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.model.BudgetStatus;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
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

    @Override
    public ResponseEntity<Budget> create(Budget budget) {

        Optional<MinorJob> minorJob = minorJobRepository.findById(budget.getMinorJob().getId());
        if (!minorJob.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        budget.setMinorJob(minorJob.get());
        return super.create(budget);
    }

    public ResponseEntity<List<Budget>> getBudgetsbyCommonUserMail(String email){
        List<Budget> user_budgets = budgetRepository.findByuserEmail(email);
        return user_budgets.size() == 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(user_budgets, HttpStatus.OK);
    }
    public ResponseEntity<Budget> requestBudget(Budget budget){
        String email_content = "";
        if (budget.getImage_url_encoded() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //Ver si es opcional.
        if (budget.getBudgetStatus().equals(BudgetStatus.BUDGETACCEPTED) || budget.getBudgetStatus().equals(BudgetStatus.BUDGETREJECTED)){
            email_content = Constant.CALLED_SERVICE+minorJobRepository.findById(budget.getMinorJob().getId()).get().getTitle()+Constant.HAS_BEEN+budget.getBudgetStatus().getStatus()+Constant.BY_THE_USER;
            emailService.sendEmail(email_content, minorJobRepository.findById(budget.getMinorJob().getId()).get().getServiceProvider().getEmail(), Constant.BUDGET_REQUEST);
            return update(budget);
        }else {
            budget.setBudgetStatus(BudgetStatus.BUDGETONHOLD);
            email_content = Constant.HAS_A_BUDGET_REQUEST+minorJobRepository.findById(budget.getMinorJob().getId()).get().getTitle();
            emailService.sendEmail(email_content, minorJobRepository.findById(budget.getMinorJob().getId()).get().getServiceProvider().getEmail(), Constant.BUDGET_REQUEST);
            return create(budget);
        }
    }
}
