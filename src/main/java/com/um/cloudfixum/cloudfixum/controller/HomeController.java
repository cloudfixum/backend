package com.um.cloudfixum.cloudfixum.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.um.cloudfixum.cloudfixum.model.AuthenticationRequest;
import com.um.cloudfixum.cloudfixum.model.AuthenticationResponse;
import com.um.cloudfixum.cloudfixum.repository.ProviderUserRepository;
import com.um.cloudfixum.cloudfixum.security.JwtUtil;
import com.um.cloudfixum.cloudfixum.security.MyUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.service.ProviderUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class HomeController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final ProviderUserRepository providerUserRepository;

    public HomeController(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtUtil, ProviderUserService providerUserService, ProviderUserRepository providerUserRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.providerUserRepository = providerUserRepository;
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

            Map<String, String> response = new HashMap<>();

            response.put("status", "403");
            response.put("Error", "Forbidden");
            response.put("message", "Incorrect username or password");

            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

    }
    @GetMapping("/currentuser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map> giveCurrentUser(Authentication authentication){
        if (authentication != null ){
            ProviderUser fullUser = providerUserRepository.findByEmail(authentication.getName());

            Map<String,String> userLogged = new HashMap<>();
            userLogged.put("id",fullUser.getId().toString());
            userLogged.put("name",fullUser.getName());
            userLogged.put("last_name",fullUser.getLast_name());
            userLogged.put("email",fullUser.getEmail());

            return new ResponseEntity<>(userLogged,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}