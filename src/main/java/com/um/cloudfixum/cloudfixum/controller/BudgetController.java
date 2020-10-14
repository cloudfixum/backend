package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.model.BudgetStatus;
import com.um.cloudfixum.cloudfixum.service.BudgetService;
import com.um.cloudfixum.cloudfixum.service.EmailService;
import com.um.cloudfixum.cloudfixum.service.MinorJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;
    private final EmailService emailService;
    private final MinorJobService minorJobService;

    public BudgetController(BudgetService budgetService, EmailService emailService, MinorJobService minorJobService) {
        this.budgetService = budgetService;
        this.emailService = emailService;
        this.minorJobService = minorJobService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetByID(@PathVariable Long id){ return budgetService.getById(id);}

    @GetMapping("/filter")
    public  ResponseEntity<List<Budget>> getBudgetsByUserEmail(@RequestParam(value = "email", required = true) String email){return budgetService.getBudgetsbyCommonUserMail(email);}

    @PostMapping
    public ResponseEntity<Budget> addBudget(@Valid @RequestBody Budget budget){
        return  budgetService.create(budget);
    }

    @PutMapping
    public ResponseEntity<Budget> updateBudget(@Valid @RequestBody Budget budget, Authentication authentication){
        if (authentication == null || !authentication.isAuthenticated() || !minorJobService.getServiceProviderByToken(authentication).getEmail().equals(minorJobService.getRepository().findById(budget.getMinorJob().getId()).get().getServiceProvider().getEmail()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return  budgetService.update(budget);
    }

    @PostMapping("/manage")
    public ResponseEntity<Budget> controlBudget(@Valid @RequestBody Budget budget){
        return budgetService.requestBudget(budget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBudget(@PathVariable Long id){
        Optional<Budget> budget = budgetService.getRepository().findById(id);
        if (!budget.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return budgetService.delete(id);
    }



}
