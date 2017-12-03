/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.common.base.Splitter;
import com.ttevent.dao.ProfileDao;
import com.ttevent.domain.Category;
import com.ttevent.domain.Location;
import com.ttevent.domain.UserProfile;
import com.ttevent.entity.UserEntity;
import com.ttevent.mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

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

  @Autowired
  private ValidationService validationService;

  public UserProfile getConnectedUserProfile() {
    UserProfile userProfile = getProfileByTwitterId(twitter.userOperations().getProfileId());

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
  public boolean saveProfile(MultiValueMap<String, Object> formData) {
    List<Exception> formErrors = validationService.validateFormData(formData);

    if (CollectionUtils.isEmpty(formErrors)) {
      UserProfile userProfile = getConnectedUserProfile();
      userProfile.setEmail(formData.get("email").toString().replace("[", "").replace("]", ""));
      userProfile.setPreferredLocations(createLocationList(formData.get("locations")));
      userProfile.setPreferredCategories(createCategoryList(formData.get("categories")));
      userProfile.setSearchKeywords(createSearchKeywords(formData.get("searchKeywords")));
      userProfile.setNotificationChannel(
              formData.get("notificationChannel").toString().replace("]", "").replace("[", ""));
      userProfile.setReceiveNotifications(formData.containsKey("receiveNotifications"));
      UserEntity entity = mapper.convert(userProfile);
      dao.persist(entity);
      return true;
    } else {
      return false;
    }

  }

  private List<String> createSearchKeywords(Object searchKeywords) {
    if (StringUtils.isEmpty(searchKeywords)) {
      return null;
    }
    List<String> tempList =
            Lists.newArrayList(Splitter.on(',').split(searchKeywords.toString().replace("[", "").replace("]", "")));

    List<String> searchKeywordList = new ArrayList<>();
    for (String searchKeyword : tempList) {
      if (!searchKeyword.trim().isEmpty()) {
        searchKeywordList.add(searchKeyword);
      }
    }
    return searchKeywordList;
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

  public String getSearchKeywordsInString(List<String> searchKeywords) {
    if (CollectionUtils.isEmpty(searchKeywords)) {
      return "";
    }
    StringBuilder base = new StringBuilder();
    for (String searchKeyword : searchKeywords) {
        base.append(searchKeyword + ",");
    }
    return base.toString().substring(0, base.toString().length() - 1);
  }

  public UserProfile getProfileByTwitterId(long twitterProfileId) {
    UserEntity userEntity = dao.findByTwitterId(twitterProfileId);
    UserProfile userProfile;
    if (userEntity == null) {
      userProfile = UserProfile.builder().twitterId(twitterProfileId).build();
    } else {
      userProfile = mapper.convert(userEntity);
    }
    return userProfile;
  }
}