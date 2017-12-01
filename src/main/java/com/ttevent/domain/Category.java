/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.domain;

import com.ttevent.entity.AbstractEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author huseyin.kilic
 */
@Data
@Builder
public class Category {

  private Long id;
  private String adi;
  private String radi;

}
