/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.hkilic.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * @author huseyin.kilic
 */
@Controller
public class BaseController {

  @Inject
  protected ConnectionRepository connectionRepository;

  @Inject
  protected Twitter twitter;


  public void fillProfile(Model model) {
    TwitterProfile twitterProfile = twitter.userOperations().getUserProfile();

    String profileImageUrl = twitterProfile.getProfileImageUrl();
    if (profileImageUrl.contains("default")) {
      profileImageUrl = "http://lorempixel.com/400/200/";
    }


    model.addAttribute("twitterProfile", twitterProfile);
    model.addAttribute("profileImageUrl", profileImageUrl);
  }

}