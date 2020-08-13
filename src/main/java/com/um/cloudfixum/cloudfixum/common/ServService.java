package com.um.cloudfixum.cloudfixum.common;

import com.um.cloudfixum.cloudfixum.model.JobService;
import com.um.cloudfixum.cloudfixum.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ServService extends GenericServiceImpl<JobService>{
    @Autowired
    private final ServiceRepository serviceRepository;

    public ServService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public JpaRepository<JobService, Long> getRepository() {
        return serviceRepository;
    }
}
