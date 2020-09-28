package com.um.cloudfixum.cloudfixum.security;

import com.um.cloudfixum.cloudfixum.repository.ProviderUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final ProviderUserRepository providerUserRepository;

    public MyUserDetailsService(ProviderUserRepository providerUserRepository) {
        this.providerUserRepository = providerUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails user = providerUserRepository.findByEmail(s);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("Username Not found");
    }
}
