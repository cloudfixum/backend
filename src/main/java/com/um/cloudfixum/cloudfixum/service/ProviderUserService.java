package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.model.BudgetStatus;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.repository.BudgetRepository;
import com.um.cloudfixum.cloudfixum.repository.MinorJobRepository;
import com.um.cloudfixum.cloudfixum.repository.ProviderUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProviderUserService extends GenericServiceImpl<ProviderUser> {

    private final ProviderUserRepository providerUserRepository;
    private final BudgetRepository budgetRepository;
    private final MinorJobRepository minorJobRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public ProviderUserService(ProviderUserRepository providerUserRepository, BudgetRepository budgetRepository, MinorJobRepository minorJobRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.providerUserRepository = providerUserRepository;
        this.budgetRepository = budgetRepository;
        this.minorJobRepository = minorJobRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public ResponseEntity<List<MinorJob>> getJobs(Long id) {
        if (!providerUserRepository.findById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<MinorJob> userJobs = providerUserRepository.findById(id).get().getServiceList();

        return new ResponseEntity<>(userJobs, HttpStatus.OK);

    }

    public ResponseEntity<List<Budget>> getBudgets(Authentication auth) {
        ProviderUser user = providerUserRepository.findByEmail(auth.getName());
        List<Budget> budgets = new ArrayList<>();

        for (MinorJob job : user.getServiceList()) {
            budgets.addAll(job.getBudgetList());
        }

        budgets.sort((b1, b2) -> b2.getId().compareTo(b1.getId()));

        return budgets.size() == 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderUser> create(ProviderUser user) {
        if (providerUserRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        getRepository().save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public JpaRepository<ProviderUser, Long> getRepository() {
        return providerUserRepository;
    }

    public ResponseEntity <Map<String,Float>> getAverage(Long id) {
        if (!providerUserRepository.findById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Budget> budgetsUser = getQualifyBudgetByUser(id);
        Float count = declaresCount();
        if (budgetsUser.size() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Budget budget : budgetsUser){
            count += budget.getQualification();
        }

        Float average = count/budgetsUser.size();
        Map<String, Float> userAvg = new HashMap<>();
        userAvg.put("average",average);

        return average == 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(userAvg,HttpStatus.OK);
    }

    private Float declaresCount(){
        return 0.0f;
    }

    private List<Budget> getQualifyBudgetByUser(Long id) {

        return budgetRepository.findAllByMinorJobServiceProviderId(id)
                .stream()
                .filter(b -> b.getBudgetStatus().equals(BudgetStatus.BUDGET_ACCEPTED))
                .filter(b -> b.getQualification() != null)
                .collect(Collectors.toList());
    }
}
