/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.hkilic.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/connect")
public class CustomConnectController extends ConnectController {

  public CustomConnectController(ConnectionFactoryLocator connectionFactoryLocator,
          ConnectionRepository connectionRepository) {
    super(connectionFactoryLocator, connectionRepository);
  }

  @Override
  protected String connectView(String providerId) {
    return "redirect:/login";
  }

  @Override
  protected String connectedView(String providerId) {
    return "redirect:/profile";
  }
}