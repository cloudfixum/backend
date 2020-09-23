package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.repository.MinorJobRepository;
import com.um.cloudfixum.cloudfixum.repository.ProviderUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
public class MinorJobService extends GenericServiceImpl<MinorJob> {

    private final MinorJobRepository minorJobRepository;
    private final ProviderUserRepository providerUserRepository;

    public MinorJobService(MinorJobRepository minorJobRepository, ProviderUserRepository providerUserRepository) {
        this.minorJobRepository = minorJobRepository;
        this.providerUserRepository = providerUserRepository;
    }

    @Override
    public ResponseEntity<MinorJob> create(MinorJob job) {

        Optional<ProviderUser> serviceProvider = providerUserRepository.findById(job.getServiceProvider().getId());
        if (!serviceProvider.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        for (MinorJob minorJob : serviceProvider.get().getServiceList()) {
            if (minorJob.equals(job)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }

        job.setServiceProvider(serviceProvider.get());
        return super.create(job);
    }

    public ResponseEntity<List<MinorJob>> filterByTitle(String query){
        if (minorJobRepository.findByTitleContaining(query).size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(minorJobRepository.findByTitleContaining(query),HttpStatus.OK);
    }

    /*public ResponseEntity<List<MinorJob>> filterBySubCategory(String query){
        if (minorJobRepository.findByCategoryName(query).size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(minorJobRepository.findByCategoryName(query),HttpStatus.OK);
    }*/

    @Override
    public JpaRepository<MinorJob, Long> getRepository() {
        return minorJobRepository;
    }
}
