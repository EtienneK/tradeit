package com.etiennek.auth.serv;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.etiennek.auth.api.CurrentUserService;
import com.etiennek.auth.api.User;

import rx.Observable;

@Service
class CurrentUserServiceImpl implements CurrentUserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Observable<User> get() {
    Authentication authentication = SecurityContextHolder.getContext()
                                                         .getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return Observable.just(null);
    }
    return Observable.just((User) authentication.getPrincipal());
  }

  @Override
  public Observable<Void> setAttributes(Map<String, Object> attributes) {
    User currentUser = get().toBlocking()
                            .first();
    if (currentUser == null) {
      return Observable.empty();
    }

    currentUser.setAttributes(attributes);
    userRepository.save(currentUser);
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
    SecurityContextHolder.getContext()
                         .setAuthentication(authentication);
    return Observable.empty();
  }

}
