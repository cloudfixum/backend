package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.repository.BudgetRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetService extends GenericServiceImpl<Budget> {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public JpaRepository<Budget, Long> getRepository() {
        return budgetRepository;
    }
}
