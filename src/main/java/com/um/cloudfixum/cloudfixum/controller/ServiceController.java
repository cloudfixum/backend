package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.common.ServService;
import com.um.cloudfixum.cloudfixum.model.JobService;
<<<<<<< HEAD
=======
import com.um.cloudfixum.cloudfixum.repository.ServiceRepository;
>>>>>>> testing
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.List;
=======
import javax.validation.Valid;
>>>>>>> testing

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
