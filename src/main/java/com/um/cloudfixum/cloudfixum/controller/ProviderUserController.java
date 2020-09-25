package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.service.ProviderUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class ProviderUserController {
    private final ProviderUserService providerUserService;

    public ProviderUserController(ProviderUserService providerUserService) {
        this.providerUserService = providerUserService;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id, Authentication authentication) {
        if (!authentication.getName().equals(providerUserService.getRepository().getOne(id).getEmail())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return providerUserService.delete(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProviderUser> updateUser(@Valid @RequestBody ProviderUser providerUser) {
        return providerUserService.update(providerUser);
    }

}
