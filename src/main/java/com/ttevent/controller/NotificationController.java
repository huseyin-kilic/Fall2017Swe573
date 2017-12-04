/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.controller;

import com.ttevent.service.NotificationService;
import io.swagger.client.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping(path = "/notification")
public class NotificationController extends BaseController {

  @Autowired
  private NotificationService notificationService;

  @RequestMapping("/{twitterId}")
  @ResponseBody
  public String contactView(@PathVariable("twitterId") Long twitterId) throws ApiException {
      notificationService.sendNotificationToUser(twitterId);
      return "notification triggered for user " + twitterId + "...";
  }
}