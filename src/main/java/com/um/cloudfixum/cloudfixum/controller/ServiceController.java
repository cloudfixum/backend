package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.common.ServService;
import com.um.cloudfixum.cloudfixum.model.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<JobService>> getAllService(){
        return servService.getAll();
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
    public ResponseEntity<JobService> updateService(JobService jobService){
        return servService.update(jobService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable Long id){
        return servService.delete(id);
    }



}
