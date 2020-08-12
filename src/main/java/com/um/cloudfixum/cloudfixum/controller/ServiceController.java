package com.um.cloudfixum.cloudfixum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/service")
public class ServiceController {

    private final long var = 8 ;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getAllClients(){
        long lista = 3;
    }

    @GetMapping("/{id}")
    public void getServiceByID(@PathVariable Long id){
        long lista = 3;
    }

    @PostMapping
    public void addService(){
        String service = "Service";
    }

    @PutMapping
    public void updateService(){
        String service = "Service 2";
    }

    @PutMapping("/{id}")
    public void deleteService(@PathVariable Long id){
        long lista = 2;
    }



}
