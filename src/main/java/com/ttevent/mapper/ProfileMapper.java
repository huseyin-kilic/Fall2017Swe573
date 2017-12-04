/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.mapper;

import com.ttevent.domain.UserProfile;
import com.ttevent.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huseyin.kilic
 */
@Component
public class ProfileMapper {

  @Autowired
  private LocationMapper locationMapper;

  @Autowired
  private CategoryMapper categoryMapper;

  public UserProfile convert(UserEntity entity) {
    return UserProfile.builder()
            .id(entity.getId())
            .twitterId(entity.getTwitterId())
            .name(entity.getName())
            .email(entity.getEmail())
            .preferredCategories(categoryMapper.convertToDtoList(entity.getPreferredCategories()))
            .preferredLocations(locationMapper.convertToDtoList(entity.getPreferredLocations()))
            .searchKeywords(entity.getSearchKeywords())
            .notificationChannel(entity.getNotificationChannel())
            .receiveNotifications(entity.isReceiveNotifications())
            .build();
  }

  public UserEntity convert(UserProfile dto) {
    return UserEntity.builder()
            .id(dto.getId())
            .twitterId(dto.getTwitterId())
            .name(dto.getName())
            .email(dto.getEmail())
            .preferredCategories(categoryMapper.convertToEntityList(dto.getPreferredCategories()))
            .preferredLocations(locationMapper.convertToEntityList(dto.getPreferredLocations()))
            .searchKeywords(dto.getSearchKeywords())
            .notificationChannel(dto.getNotificationChannel())
            .receiveNotifications(dto.isReceiveNotifications())
            .build();
  }

}
