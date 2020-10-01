package com.um.cloudfixum.cloudfixum.repository;

import com.um.cloudfixum.cloudfixum.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
