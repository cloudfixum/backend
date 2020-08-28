package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.repository.ProviderUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProviderUserService extends GenericServiceImpl<ProviderUser> {

    @Autowired
    private final ProviderUserRepository providerUserRepository;

    public ProviderUserService(ProviderUserRepository providerUserRepository) {
        this.providerUserRepository = providerUserRepository;
    }


    @Override
    public JpaRepository<ProviderUser, Long> getRepository() {
        return providerUserRepository;
    }
}
