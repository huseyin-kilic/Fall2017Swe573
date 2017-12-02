/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.social.twitter.api.TwitterProfile;

import java.util.List;

/**
 * @author huseyin.kilic
 */
@Data
@Builder
public class UserProfile {

  private Long id;
  private long twitterId;
  private TwitterProfile twitterProfile;
  private String profileImageUrl;
  private List<Location> preferredLocations;
  private List<Category> preferredCategories;
  private List<String> searchKeywords;
  private String notificationChannel;
  private boolean receiveNotifications;


}
