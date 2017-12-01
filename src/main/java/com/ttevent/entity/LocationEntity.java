/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author huseyin.kilic
 */
@Entity(name = "location")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity extends AbstractEntity {

  @Id
  private Long id;

  private String adi;

  private String radi;

}
