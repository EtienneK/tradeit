package com.etiennek.trade.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.etiennek.auth.api.User;
import com.etiennek.auth.api.UserService;

@Service
class InitDataConfiguration {
  @Autowired
  private UserService userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostConstruct
  public void init() {
    userRepository.create(new User("EtienneK", "test@example.com", "passw0rd", passwordEncoder))
                  .toBlocking()
                  .first();
  }

}
