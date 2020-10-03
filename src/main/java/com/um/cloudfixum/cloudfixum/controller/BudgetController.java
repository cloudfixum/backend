package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.email.EmailBody;
import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.service.BudgetService;
import com.um.cloudfixum.cloudfixum.service.EmailService;
import com.um.cloudfixum.cloudfixum.service.MinorJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Budget> addBudget(@Valid @RequestBody Budget budget){
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail(minorJobService.getRepository().findById(budget.getMinorJob().getId()).get().getServiceProvider().getEmail());
        emailBody.setContent("<h1>PRESUPUESTO!"+budget.getDescription()+"</h1>");
        emailBody.setSubject("Solicitud de presupuesto:");
        emailService.sendEmail(emailBody);
        return  budgetService.create(budget);
    }

    @PutMapping
    public ResponseEntity<Budget> updateBudget(@Valid @RequestBody Budget budget, Authentication authentication){
        if (authentication == null || !authentication.isAuthenticated() || !minorJobService.getServiceProviderByToken(authentication).getEmail().equals(minorJobService.getRepository().findById(budget.getMinorJob().getId()).get().getServiceProvider().getEmail()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        budget.setBudget_confirmation(Boolean.TRUE);
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail(budget.getUser_email());
        emailBody.setContent("<h1>PRESUPUESTO!"+budget.getProvider_response()+"</h1>");
        emailBody.setSubject("No te puedo atender así bro.");
        emailService.sendEmail(emailBody);
        return  budgetService.update(budget);
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
