package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.service.JobServiceService;
import com.um.cloudfixum.cloudfixum.model.JobService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://cloudfixum-develop.herokuapp.com"})
@RequestMapping("/api/service")
public class ServiceController {

    private final JobServiceService jobServiceService;

    public ServiceController(JobServiceService jobServiceService) {
        this.jobServiceService = jobServiceService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<JobService>> getAllService(){
        return jobServiceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobService> getServiceByID(@PathVariable Long id){
        return jobServiceService.getById(id);
    }

    @PostMapping
    public ResponseEntity<JobService> addService(@Valid @RequestBody JobService service){
        service.setDate(LocalDate.now());
        service.setImage_url(service.getCategory().getImage_url());

        return jobServiceService.create(service);
    }

    @PutMapping
    public ResponseEntity<JobService> updateService(@Valid @RequestBody JobService jobService){
        jobService.setImage_url(jobService.getCategory().getImage_url());

        return jobServiceService.update(jobService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable Long id){
        return jobServiceService.delete(id);
    }
    @GetMapping("/paged/{page}")
    public Page<JobService> getByPage(@PathVariable("page") int page){
        System.out.println(jobServiceService.getAll().getBody());
        return jobServiceService.findServiceByPage(page);
    }


}
