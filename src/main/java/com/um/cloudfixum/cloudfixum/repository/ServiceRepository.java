package com.um.cloudfixum.cloudfixum.repository;
import com.um.cloudfixum.cloudfixum.model.JobService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository <JobService, Long> {
    
}
