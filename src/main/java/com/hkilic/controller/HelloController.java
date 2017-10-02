/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.hkilic.controller;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/")
public class HelloController {

  @Inject
  private Twitter twitter;

  @Inject
  private ConnectionRepository connectionRepository;

  @RequestMapping(method=RequestMethod.GET)
  public String helloTwitter(Model model) {
    if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
      return "redirect:/connect/twitter";
    }

    model.addAttribute(twitter.userOperations().getUserProfile());
    CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
    model.addAttribute("friends", friends);
    return "hello";
  }

}