/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.hkilic.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author huseyin.kilic
 */
@Entity(name = "dummy")
@Data
public class DummyEntity {

  @Id
  @GeneratedValue(generator = "dummy_s")
  @SequenceGenerator(name ="dummy_s", sequenceName = "dummy_s", allocationSize = 25)
  private Long id;
  private String detail;

}
