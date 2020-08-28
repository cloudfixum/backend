package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.repository.MinorJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MinorJobService extends GenericServiceImpl<MinorJob> {
    @Autowired
    private final MinorJobRepository minorJobRepository;

    public MinorJobService(MinorJobRepository minorJobRepository) {
        this.minorJobRepository = minorJobRepository;
    }

    @Override
    public JpaRepository<MinorJob, Long> getRepository() {
        return minorJobRepository;
    }
}
