/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.hkilic.controller;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController{

  @RequestMapping
  public String profileView(Model model) {
    fillProfile(model);
    if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
      return "redirect:/login";
    }
    return "profile";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String saveProfile(@RequestBody MultiValueMap<Object, Object> formData, Model model) {
    System.out.println(formData);
    fillProfile(model);
    return "profile";
  }

}