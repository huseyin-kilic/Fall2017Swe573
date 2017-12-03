/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.controller;

import com.ttevent.service.EventService;
import io.swagger.client.ApiException;
import io.swagger.client.model.Etkinlik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController extends BaseController {

  @Autowired
  private EventService eventService;

  @RequestMapping
  public String dashboardView(Model model) throws ApiException {
    fillProfile(model);
    List<Etkinlik> events = eventService.searchEventsForUser(twitter.userOperations().getProfileId());
    model.addAttribute("events", events);
    return "dashboard";
  }


}