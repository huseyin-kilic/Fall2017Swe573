/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.controller;

import com.ttevent.domain.UserProfile;
import com.ttevent.exception.InvalidCategoryException;
import com.ttevent.exception.InvalidLocationException;
import com.ttevent.service.EventService;
import com.ttevent.service.ProfileService;
import com.ttevent.service.ValidationService;
import io.swagger.client.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;

/**
 * @author huseyin.kilic
 */
@Controller
public class BaseController {

  @Autowired
  protected ConnectionRepository connectionRepository;

  @Autowired
  protected ProfileService profileService;

  @Autowired
  protected EventService eventService;

  @Autowired
  protected ValidationService validationService;

  public void fillProfile(Model model) throws ApiException {
    UserProfile user = profileService.getUserProfile();

    model.addAttribute("user", user);
    model.addAttribute("twitterProfile", user.getTwitterProfile());
    model.addAttribute("profileImageUrl", user.getProfileImageUrl());
    model.addAttribute("locations", eventService.getLocations());
    model.addAttribute("categories", eventService.getCategories());
    model.addAttribute("preferredLocations",
            profileService.getPreferredLocationsInString(user.getPreferredLocations()));
    model.addAttribute("preferredCategories",
            profileService.getPreferredCategoriesInString(user.getPreferredCategories()));
    model.addAttribute("searchKeywords",
            profileService.getSearchKeywordsInString(user.getSearchKeywords()));
    model.addAttribute("notificationChannel", user.getNotificationChannel());
    model.addAttribute("receiveNotifications", user.isReceiveNotifications());
    model.addAttribute("locationValidationErrors", validationService.getValidationErrorsInString(
            InvalidLocationException.class));
    model.addAttribute("categoryValidationErrors", validationService.getValidationErrorsInString(
            InvalidCategoryException.class));
    validationService.setValidationErrors(new ArrayList<Exception>());
  }

}