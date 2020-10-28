package com.um.cloudfixum.cloudfixum.repository;

import com.um.cloudfixum.cloudfixum.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List <Budget> findByuserEmail(String user_email);
    List <Budget> findAllByMinorJobServiceProviderId (Long id);
    List <Budget> findAllByMinorJobId(Long id);
}
