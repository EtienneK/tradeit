package com.etiennek.auth.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

class RegisterViewModel {

  @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters in length, inclusive")
  @Pattern(regexp = "^[0-9A-Za-z]+[0-9A-Za-z_]*$",
      message = "Username must start with a number or letter and may only contain numbers, letters and underscores")
  private String username;
  @Size(max = 128, message = "Email address is too long")
  @Email(message = "Invalid email address")
  private String emailAddress;
  @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters in length, inclusive")
  private String password;
  private String passwordRepeat;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordRepeat() {
    return passwordRepeat;
  }

  public void setPasswordRepeat(String passwordRepeat) {
    this.passwordRepeat = passwordRepeat;
  }

}
