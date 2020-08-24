package com.um.cloudfixum.cloudfixum.repository;

import com.um.cloudfixum.cloudfixum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
