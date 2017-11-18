/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.hkilic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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