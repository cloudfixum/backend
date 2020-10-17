package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.model.BudgetRequest;
import com.um.cloudfixum.cloudfixum.model.BudgetResponse;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
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
    private final MinorJobService minorJobService;

    public BudgetController(BudgetService budgetService, EmailService emailService, MinorJobService minorJobService) {
        this.budgetService = budgetService;
        this.minorJobService = minorJobService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetByID(@PathVariable Long id){ return budgetService.getById(id);}

    @GetMapping("/filter")
    public  ResponseEntity<List<Budget>> getBudgetsByUserEmail(@RequestParam(value = "email") String email){return budgetService.getBudgetsbyCommonUserMail(email);}

    @PostMapping
    public ResponseEntity<?> addBudget(@Valid @RequestBody BudgetRequest budgetRequest){

        return budgetService.create(budgetRequest);
    }


    @PostMapping("/answer")
    public ResponseEntity<?> responseBudget(@Valid @RequestBody BudgetResponse budgetResponse,Authentication authentication){
        if (budgetResponse.getBudgetId() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Budget> budget = budgetService.getRepository().findById(budgetResponse.getBudgetId());
        if (!budget.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (authentication == null || !authentication.isAuthenticated() || !minorJobService.getServiceProviderByToken(authentication).getEmail().equals(budget.get().getMinorJob().getServiceProvider().getEmail()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return budgetService.answerBudget(budgetResponse);
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
