package com.etiennek.auth.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rx.Observable;

import com.etiennek.auth.api.User;
import com.etiennek.auth.api.UserQueryService;

@Service
class UserQueryServiceImpl implements UserQueryService, UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Observable<User> findByUsername(String username) {
    return Observable.just(userRepository.findByUsernameIgnoreCase(username));
  }

  @Override
  public Observable<User> findByEmailAddress(String emailAddress) {
    return Observable.just(userRepository.findByEmailAddressIgnoreCase(emailAddress));
  }

  @Override
  public UserDetails loadUserByUsername(String emailAddressOrUsername) throws UsernameNotFoundException {
    User user = userRepository.findByEmailAddressIgnoreCase(emailAddressOrUsername);
    if (user == null) {
      user = userRepository.findByUsernameIgnoreCase(emailAddressOrUsername);
    }
    if (user == null) {
      throw new UsernameNotFoundException("No such user found");
    }
    return user;
  }

}
