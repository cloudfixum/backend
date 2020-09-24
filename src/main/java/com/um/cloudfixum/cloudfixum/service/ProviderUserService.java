package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.repository.ProviderUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderUserService extends GenericServiceImpl<ProviderUser> {

    private final ProviderUserRepository providerUserRepository;

    public ProviderUserService(ProviderUserRepository providerUserRepository) {
        this.providerUserRepository = providerUserRepository;
    }

    public ResponseEntity<List<MinorJob>> getJobs(Long id) {
        if (!providerUserRepository.findById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<MinorJob> userJobs = providerUserRepository.findById(id).get().getServiceList();

        return new ResponseEntity<>(userJobs, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProviderUser> create(ProviderUser user) {
        if(providerUserRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return super.create(user);
    }

    @Override
    public JpaRepository<ProviderUser, Long> getRepository() {
        return providerUserRepository;
    }
}
