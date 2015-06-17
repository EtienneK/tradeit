package com.etiennek.auth.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;

import com.etiennek.auth.api.User;
import com.etiennek.auth.api.UserService;

@Service
class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Observable<Long> create(User user) {
    return Observable.just(userRepository.save(user)
                                         .getId());
  }

}
