package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.User;
import com.um.cloudfixum.cloudfixum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserService extends GenericServiceImpl<User> implements UserDetailsService{
    @Autowired
    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User usuario = new User();
        usuario.setId((long) 1);
        usuario.setEmail("saldasldl");
        usuario.setPassword("hashedpass");
        usuario.setUsername("Elzeta");

        return new User();
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return null;
    }

}
