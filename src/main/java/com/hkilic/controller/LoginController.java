/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.hkilic.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/")
public class LoginController {

  @Inject
  private ConnectionRepository connectionRepository;

  @RequestMapping
  public String redirectToLogin() {
    return "redirect:/login";
  }

  @RequestMapping("/login")
  public String loginView() {
    if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
      return "login";
    }
    return "redirect:/profile";
  }


}