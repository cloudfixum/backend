package com.um.cloudfixum.cloudfixum.repository;
import com.um.cloudfixum.cloudfixum.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository <Service, Long> {
    
}
