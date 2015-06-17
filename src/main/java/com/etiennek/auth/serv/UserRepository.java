package com.etiennek.auth.serv;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etiennek.auth.api.User;

@Repository
interface UserRepository extends CrudRepository<User, Long> {

  User findByUsernameIgnoreCase(String username);

  User findByEmailAddressIgnoreCase(String emailAddress);

}
