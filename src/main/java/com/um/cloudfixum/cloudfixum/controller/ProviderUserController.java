package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.model.Budget;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.service.MinorJobService;
import com.um.cloudfixum.cloudfixum.service.ProviderUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class ProviderUserController {
    private final ProviderUserService providerUserService;
    private final MinorJobService minorJobService;

    public ProviderUserController(ProviderUserService providerUserService, MinorJobService minorJobService) {
        this.providerUserService = providerUserService;
        this.minorJobService = minorJobService;
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
    public ResponseEntity<List<Budget>> getBudgetsByServiceProvider(Authentication authentication) {
        if (authentication == null) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return providerUserService.getBudgets(authentication);
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

    @GetMapping("/{id}/average")
    public ResponseEntity<Float> getAverageBudgetsByServiceProvider(@PathVariable Long id) {
        return providerUserService.getAverage(id);
    }

}
