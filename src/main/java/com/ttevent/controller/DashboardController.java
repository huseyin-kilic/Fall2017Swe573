/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController extends BaseController {

  @RequestMapping
  public String dashboardView(Model model) {
    fillProfile(model);
    return "dashboard";
  }
}