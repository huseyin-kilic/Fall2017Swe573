/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author huseyin.kilic
 */
@Data
@Builder
public class Location {

  private Long id;
  private String adi;
  private String radi;

}
