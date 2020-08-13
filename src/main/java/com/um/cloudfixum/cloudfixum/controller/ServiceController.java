package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.common.ServService;
import com.um.cloudfixum.cloudfixum.model.JobService;
import com.um.cloudfixum.cloudfixum.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/service")
public class ServiceController {

    private final ServService servService;

    public ServiceController(ServService servService) {
        this.servService = servService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getAllClients(){
        long lista = 3;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobService> getServiceByID(@PathVariable Long id){
        return servService.getById(id);
    }

    @PostMapping
    public ResponseEntity<JobService> addService(@Valid @RequestBody JobService service){
        return servService.create(service);
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
