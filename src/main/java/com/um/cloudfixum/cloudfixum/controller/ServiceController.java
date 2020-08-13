package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.service.ServService;
import com.um.cloudfixum.cloudfixum.model.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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
    public ResponseEntity<JobService> getServiceByID(@PathVariable Long id){
        return servService.getById(id);
    }

    @PostMapping
    public ResponseEntity<JobService> addService(@Valid @RequestBody JobService service){
        service.setDate(LocalDate.now());
        return servService.create(service);
    }

    @PutMapping
    public ResponseEntity<JobService> updateService(@Valid @RequestBody JobService jobService){
        return servService.update(jobService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable Long id){
        return servService.delete(id);
    }



}
