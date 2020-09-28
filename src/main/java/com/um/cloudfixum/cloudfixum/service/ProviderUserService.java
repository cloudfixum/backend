package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.repository.ProviderUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderUserService extends GenericServiceImpl<ProviderUser> {

    private final ProviderUserRepository providerUserRepository;
    private final PasswordEncoder passwordEncoder;

    public ProviderUserService(ProviderUserRepository providerUserRepository, PasswordEncoder passwordEncoder) {
        this.providerUserRepository = providerUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<List<MinorJob>> getJobs(Long id) {
        if (!providerUserRepository.findById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<MinorJob> userJobs = providerUserRepository.findById(id).get().getServiceList();

        return new ResponseEntity<>(userJobs, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProviderUser> create(ProviderUser user) {
        if (providerUserRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        getRepository().save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public JpaRepository<ProviderUser, Long> getRepository() {
        return providerUserRepository;
    }
}
