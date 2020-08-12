package com.um.cloudfixum.cloudfixum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/service")
public class ServiceController {

    private final long var =8 ;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getAllClients(){

    }


}
