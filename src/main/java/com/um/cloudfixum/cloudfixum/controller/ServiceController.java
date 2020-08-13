package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.service.ServService;
import com.um.cloudfixum.cloudfixum.model.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<JobService> jobServices = servService.getAll()
                .getBody()
                .stream()
                .filter(service -> service.getDate().isAfter(LocalDate.now().minusDays(1)))
                .sorted(Comparator.comparing(JobService::getDate)).collect(Collectors.toList());
        return new ResponseEntity<>(jobServices, HttpStatus.OK);
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
    public ResponseEntity<JobService> updateService(JobService jobService){
        return servService.update(jobService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable Long id){
        return servService.delete(id);
    }



}
