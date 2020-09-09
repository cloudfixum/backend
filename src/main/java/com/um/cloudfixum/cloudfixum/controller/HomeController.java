package com.um.cloudfixum.cloudfixum.controller;


import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.service.ProviderUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@CrossOrigin
@RestController
public class HomeController {
    private final ProviderUserService providerUserService;

    public HomeController(ProviderUserService providerUserService) {
        this.providerUserService = providerUserService;
    }

    @GetMapping
    public RedirectView home() {
        return new RedirectView("/swagger-ui.html");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ProviderUser> login(@RequestBody ProviderUser providerUser) {
        return providerUserService.findUserByDni(providerUser.getDni());
    }
}
