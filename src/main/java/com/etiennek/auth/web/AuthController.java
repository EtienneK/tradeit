package com.etiennek.auth.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rx.Observable;

import com.etiennek.auth.api.User;
import com.etiennek.auth.api.UserQueryService;
import com.etiennek.auth.api.UserService;

@Controller
@RequestMapping("/auth")
class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserQueryService userQueryService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @RequestMapping("/login")
  String login() {
    return "auth/login";
  }

  @RequestMapping("/register")
  String registerGet(@ModelAttribute RegisterViewModel registerViewModel) {
    return "auth/register";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register")
  DeferredResult<ModelAndView> registerPost(@Valid RegisterViewModel registerViewModel, BindingResult result,
      RedirectAttributes redirect) {
    DeferredResult<ModelAndView> deferredResult = new DeferredResult<>();

    if (!registerViewModel.getPassword()
                          .equals(registerViewModel.getPasswordRepeat())) {
      result.addError(new FieldError("registerViewModel", "passwordRepeat", "Passwords do not match"));
    }

    userQueryService.findByUsername(registerViewModel.getUsername())
                    .concatMap(user -> {
                      if (user != null) {
                        result.addError(new FieldError("registerViewModel", "username", "Username is already in use"));
                      }
                      return userQueryService.findByEmailAddress(registerViewModel.getEmailAddress());
                    })
                    .concatMap(
                        user -> {
                          if (user != null) {
                            result.addError(new FieldError("registerViewModel", "emailAddress",
                                "Email Address is already in use"));
                          }
                          if (result.hasErrors()) {
                            return Observable.just(null);
                          }
                          // XXX: Possible race condition here if two users type in the same
                          // username OR email but different case (upper or lower). Depending on the
                          // database, the unique constraint may not trigger
                          return userService.create(new User(registerViewModel.getUsername(),
                              registerViewModel.getEmailAddress(), registerViewModel.getPassword(), passwordEncoder));
                        })
                    .subscribe(
                        userId -> {
                          if (userId == null) {
                            deferredResult.setResult(new ModelAndView("auth/register"));
                          } else {
                            redirect.addFlashAttribute("messageSuccess",
                                "You have successfully registered. Please log in below with the account credentials you've just created.");
                            deferredResult.setResult(new ModelAndView("redirect:/auth/login"));
                          }
                        });

    return deferredResult;
  }
}
