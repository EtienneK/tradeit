package com.etiennek.auth.api;

import java.util.Map;

import rx.Observable;

public interface CurrentUserService {
  /**
   * @return the current authenticated user OR null if not authenticated
   */
  Observable<User> get();
  
  Observable<Void> setAttributes(Map<String, Object> attributes);
}
