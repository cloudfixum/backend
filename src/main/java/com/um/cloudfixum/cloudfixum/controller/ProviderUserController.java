package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.service.BudgetService;
import com.um.cloudfixum.cloudfixum.service.ProviderUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class ProviderUserController {
    private final ProviderUserService providerUserService;
    private final BudgetService budgetService;

    public ProviderUserController(ProviderUserService providerUserService, BudgetService budgetService) {
        this.providerUserService = providerUserService;
        this.budgetService = budgetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProviderUser> addUser(@Valid @RequestBody ProviderUser providerUser) {
        return providerUserService.create(providerUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProviderUser> getUserByID(@PathVariable Long id) {
        return providerUserService.getById(id);
    }

    @GetMapping("/{id}/jobs")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MinorJob>> getJobsByUserID(@PathVariable Long id) {
        return providerUserService.getJobs(id);
    }
    @GetMapping("/budgets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Budget>> getBudgetsByServiceProvider(Authentication authentication){
        List<Budget> budgets = budgetService.getRepository().findAll();
        List<Budget>providerUserBudgets = new ArrayList<>();
        if (authentication != null ) {
            System.out.println(budgets);
            for ( Budget budget : budgets) {
                if (budget.getMinorJob().getServiceProvider().getEmail().equals(authentication.getName())){
                    providerUserBudgets.add(budget);
                }
                System.out.println(providerUserBudgets);
                return new  ResponseEntity<>(providerUserBudgets,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id, Authentication authentication) {
        Optional<ProviderUser> user = providerUserService.getRepository().findById(id);
        if (authentication != null && user.isPresent() && user.get().getEmail().equals(authentication.getName())) {
            return providerUserService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping
    public ResponseEntity<ProviderUser> updateUser(@Valid @RequestBody ProviderUser providerUser, Authentication authentication) {
        Optional<ProviderUser> user = providerUserService.getRepository().findById(providerUser.getId());
        if (authentication != null && user.isPresent() && user.get().getEmail().equals(authentication.getName())) {
            return providerUserService.update(providerUser);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

}
