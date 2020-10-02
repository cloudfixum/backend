package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetByID(@PathVariable Long id){ return budgetService.getById(id);}

    @PostMapping
    public ResponseEntity<Budget> addBudget(@Valid @RequestBody Budget budget){

        return  budgetService.create(budget);
    }

    @PutMapping
    public ResponseEntity<Budget> updateBudget(@Valid @RequestBody Budget budget){

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
