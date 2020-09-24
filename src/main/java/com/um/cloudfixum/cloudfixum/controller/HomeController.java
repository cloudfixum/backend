package com.um.cloudfixum.cloudfixum.controller;


import com.um.cloudfixum.cloudfixum.model.AuthenticationRequest;
import com.um.cloudfixum.cloudfixum.model.AuthenticationResponse;
import com.um.cloudfixum.cloudfixum.security.JwtUtil;
import com.um.cloudfixum.cloudfixum.security.MyUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.service.ProviderUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@CrossOrigin
@RestController
public class HomeController {

    private final ProviderUserService providerUserService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public HomeController(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtUtil, ProviderUserService providerUserService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.providerUserService = providerUserService;
    }

    @GetMapping
    public RedirectView home() {
        return new RedirectView("/swagger-ui.html");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

    }
}