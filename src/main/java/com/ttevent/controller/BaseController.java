/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.controller;

import com.ttevent.domain.UserProfile;
import com.ttevent.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author huseyin.kilic
 */
@Controller
public class BaseController {

  @Autowired
  protected ConnectionRepository connectionRepository;

  @Autowired
  protected ProfileService profileService;


  public void fillProfile(Model model) {
    UserProfile user = profileService.getUserProfile();

    model.addAttribute("user", user);
    model.addAttribute("twitterProfile", user.getTwitterProfile());
    model.addAttribute("profileImageUrl", user.getProfileImageUrl());
  }

}