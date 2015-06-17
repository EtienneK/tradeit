package com.etiennek.auth.api;

import rx.Observable;

public interface UserService {
  Observable<Long> create(User user);
}
