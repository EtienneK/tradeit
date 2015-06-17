package com.etiennek.auth.api;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User implements UserDetails {

  private static final long serialVersionUID = 6374907020471313949L;

  @Id
  @GeneratedValue
  private Long id;

  @Email
  @Column(unique = true, nullable = false)
  private String emailAddress;
  @Pattern(regexp = "^[0-9A-Za-z]+[0-9A-Za-z_]*$")
  @Column(unique = true, nullable = false)
  private String username;
  @Column(nullable = false)
  private String password;
  private boolean enabled;
  @ElementCollection(fetch = FetchType.EAGER)
  private Map<String, String> attributes = new HashMap<>();

  @CreatedDate
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  @Version
  private long version;

  protected User() {}

  public User(String username, String emailAddress, String password, PasswordEncoder passwordEncoder) {
    this.username = username;
    this.emailAddress = emailAddress;
    this.password = passwordEncoder.encode(password);
    this.enabled = true;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setAttributes(Map<String, Object> attributes) {
    for (String key : attributes.keySet()) {
      Object value = attributes.get(key);
      if (value == null) {
        this.attributes.remove(key);
        continue;
      }
      String valueString = value.toString();
      this.attributes.put(key, valueString.substring(0, Math.min(valueString.length(), 254)));
    }
  }

  public String getAttribute(String name) {
    return attributes.get(name);
  }

}
