package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.repository.BudgetRepository;
import com.um.cloudfixum.cloudfixum.repository.MinorJobRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetService extends GenericServiceImpl<Budget> {

    private final BudgetRepository budgetRepository;
    private final MinorJobRepository minorJobRepository;

    public BudgetService(BudgetRepository budgetRepository, MinorJobRepository minorJobRepository) {
        this.budgetRepository = budgetRepository;
        this.minorJobRepository = minorJobRepository;
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

}
