package com.um.cloudfixum.cloudfixum.repository;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinorJobRepository extends JpaRepository <MinorJob, Long> {
    
}
