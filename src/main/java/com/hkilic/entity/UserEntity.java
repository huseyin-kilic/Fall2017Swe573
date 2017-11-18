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
@Entity(name = "user")
@Data
public class UserEntity {

  @Id
  @GeneratedValue(generator = "user_s")
  @SequenceGenerator(name ="user_s", sequenceName = "user_s", allocationSize = 25)
  private Long id;

  private final long twitterId;

  private String detail;

}
