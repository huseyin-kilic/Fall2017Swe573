/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.google.appengine.api.users.User;
import com.ttevent.dao.ProfileDao;
import com.ttevent.domain.UserProfile;
import com.ttevent.entity.UserEntity;
import com.ttevent.mapper.ProfileMapper;
import io.swagger.client.ApiException;
import io.swagger.client.model.Etkinlik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author huseyin.kilic
 */
@Service
@Transactional
public class NotificationService {

  @Autowired
  private Twitter twitter;

  @Autowired
  private EventService eventService;

  @Autowired
  private ProfileDao profileDao;

  @Autowired
  private ProfileMapper profileMapper;

  @Autowired
  private EmailService emailService;

  public void sendNotificationToUser(long twitterId) throws ApiException {
    UserEntity userEntity = profileDao.findByTwitterId(twitterId);
    if (userEntity == null) {
      return;
    }
    UserProfile userProfile = profileMapper.convert(userEntity);
    if (!userProfile.isReceiveNotifications()) {
      return;
    }
    
    List<Etkinlik> events = eventService.searchEventsForUser(twitterId);
    if ("email".equals(userProfile.getNotificationChannel())) {
      sendEmail(userProfile.getTwitterId(), userProfile.getEmail(), events);
    } else {
      sendDirectMessage(userProfile.getTwitterId(), events);
    }
  }

  private void sendDirectMessage(long twitterId, List<Etkinlik> events) {
    System.out.println("sending direct message notification to user: " + twitterId);
  }

  private void sendEmail(long twitterId, String email, List<Etkinlik> events) {
    System.out.println("sending email notification to user: " + twitterId + " with email address: " + email);
    emailService.send(email, "TTEvent Daily Notificaion", "this is the body");
  }

  @Scheduled(initialDelay = 20000, fixedDelay = 10000)
  public void massNotification() throws ApiException {
    List<UserEntity> activeUserList = profileDao.getActiveUsers();
    if (CollectionUtils.isEmpty(activeUserList)) {
      System.out.println("No active user found...");
      return;
    }
    for (UserEntity activeUser : activeUserList) {
      sendNotificationToUser(activeUser.getTwitterId());
    }
  }
}