/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.controller;

import org.springframework.social.NotAuthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author huseyin.kilic
 */
@ControllerAdvice
public class ErrorControllerAdvice {

  @ExceptionHandler(value = NotAuthorizedException.class)
  public String handleUnauthorizedException() {
    return "redirect:/login";
  }

}