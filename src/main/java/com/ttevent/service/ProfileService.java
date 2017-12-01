/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.ttevent.dao.ProfileDao;
import com.ttevent.domain.UserProfile;
import com.ttevent.entity.UserEntity;
import com.ttevent.mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

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

  public void saveProfile(MultiValueMap<Object, Object> formData) {
    UserProfile userProfile = getUserProfile();
    UserEntity entity = mapper.convert(userProfile);
    dao.persist(entity);
  }
}