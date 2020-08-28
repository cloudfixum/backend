package com.um.cloudfixum.cloudfixum.repository;

import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderUserRepository extends JpaRepository<ProviderUser, Long> {

}
