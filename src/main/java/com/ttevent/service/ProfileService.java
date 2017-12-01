/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.ttevent.dao.ProfileDao;
import com.ttevent.domain.Category;
import com.ttevent.domain.Location;
import com.ttevent.domain.UserProfile;
import com.ttevent.entity.UserEntity;
import com.ttevent.mapper.ProfileMapper;
import io.swagger.client.model.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Transactional
@Service
public class ProfileService {

  @Autowired
  protected Twitter twitter;

  @Autowired
  private ProfileDao dao;

  @Autowired
  private ProfileMapper mapper;

  @Autowired
  private EventService eventService;

  public UserProfile getUserProfile() {
    UserEntity userEntity = dao.findByTwitterId(twitter.userOperations().getProfileId());
    UserProfile userProfile;
    if (userEntity == null) {
      userProfile = UserProfile.builder().twitterId(twitter.userOperations().getProfileId()).build();
    } else {
      userProfile = mapper.convert(userEntity);
    }

    TwitterProfile twitterProfile = twitter.userOperations().getUserProfile();
    String profileImageUrl = twitterProfile.getProfileImageUrl();
    if (profileImageUrl.contains("default")) {
      profileImageUrl = "http://lorempixel.com/400/200/";
    }

    userProfile.setTwitterProfile(twitterProfile);
    userProfile.setProfileImageUrl(profileImageUrl);

    return userProfile;
  }

  @Transactional
  public void saveProfile(MultiValueMap<String, Object> formData) {
    UserProfile userProfile = getUserProfile();
    userProfile.setPreferredLocations(createLocationList(formData.get("locations")));
    userProfile.setPreferredCategories(createCategoryList(formData.get("categories")));
    UserEntity entity = mapper.convert(userProfile);
    dao.persist(entity);
  }

  private List<Category> createCategoryList(List<Object> categories) {
    if (CollectionUtils.isEmpty(categories)) {
      return null;
    }
    List<Category> newList = new ArrayList<>();
    for (Object categoryId : categories) {
      newList.add(eventService.getCategory(categoryId));
    }
    return newList;
  }

  private List<Location> createLocationList(List<Object> locations) {
    if (CollectionUtils.isEmpty(locations)) {
      return null;
    }
    List<Location> newList = new ArrayList<>();
    for (Object locationId : locations) {
      newList.add(eventService.getLocation(locationId));
    }
    return newList;
  }

  public String getPreferredLocationsInString(List<Location> preferredLocations) {
    if (CollectionUtils.isEmpty(preferredLocations)) {
      return "";
    }
    StringBuilder base = new StringBuilder();
    for (Location location : preferredLocations) {
      base.append(location.getAdi()).append(", ");
    }
    return base.toString().substring(0, base.toString().length() - 2);
  }

  public String getPreferredCategoriesInString(List<Category> preferredCategories) {
    if (CollectionUtils.isEmpty(preferredCategories)) {
      return "";
    }
    StringBuilder base = new StringBuilder();
    for (Category category : preferredCategories) {
      base.append(category.getAdi()).append(", ");
    }
    return base.toString().substring(0, base.toString().length() - 2);
  }
}