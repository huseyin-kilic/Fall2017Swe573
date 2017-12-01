/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author huseyin.kilic
 */
@Entity(name = "category")
@Data
@Builder
public class CategoryEntity extends AbstractEntity {

  @Id
  private Long id;

  private String adi;

  private String radi;

}
