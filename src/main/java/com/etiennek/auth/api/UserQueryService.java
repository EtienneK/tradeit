package com.etiennek.auth.api;

import rx.Observable;

public interface UserQueryService {
  Observable<User> findByUsername(String username);
  Observable<User> findByEmailAddress(String emailAddress);
}
