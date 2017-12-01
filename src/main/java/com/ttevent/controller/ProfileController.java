/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.controller;

import io.swagger.client.ApiException;
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
public class ProfileController extends BaseController {

  @RequestMapping
  public String profileView(Model model) throws ApiException {
    if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
      return "redirect:/login";
    }
    fillProfile(model);
    return "profile";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String saveProfile(@RequestBody MultiValueMap<String, Object> formData, Model model) throws ApiException {
    profileService.saveProfile(formData);
    fillProfile(model);
    return "profile";
  }


}