/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity extends AbstractEntity {

  @Id
  @GeneratedValue(generator = "user_s")
  @SequenceGenerator(name ="user_s", sequenceName = "user_s", allocationSize = 25)
  private Long id;

  private long twitterId;

  private String name;

  private String email;

  @ManyToMany
  private List<LocationEntity> preferredLocations;

  @ManyToMany
  private List<CategoryEntity> preferredCategories;

  @ElementCollection(targetClass = String.class)
  private List<String> searchKeywords;

  private String notificationChannel;

  private boolean receiveNotifications;

}