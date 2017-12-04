/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.ttevent.dao.ProfileDao;
import com.ttevent.domain.UserProfile;
import com.ttevent.entity.UserEntity;
import com.ttevent.mapper.ProfileMapper;
import io.swagger.client.ApiException;
import io.swagger.client.model.Etkinlik;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

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
      sendEmail(userProfile, userProfile.getEmail(), events);
    } else {
      sendDirectMessage(userProfile, events);
    }
  }

  private void sendDirectMessage(UserProfile userProfile, List<Etkinlik> events) {
    logger.info("sending direct message notification to user: " + userProfile.getTwitterId());
  }

  private void sendEmail(UserProfile userProfile, String emailAddress, List<Etkinlik> events) {
    if (!CollectionUtils.isEmpty(events)){
      if (events.size() > 20) {
        events = events.subList(0,20);
      }
      logger.info("sending email notification to user: " +
              userProfile.getTwitterId() + " with email address: " + emailAddress);

      emailService.send(emailAddress, events, userProfile);
    }
  }

  @Scheduled(initialDelay = 20000, fixedDelay = 3600000)
  public void massNotification() throws ApiException {
    List<UserEntity> activeUserList = profileDao.getActiveUsers();
    if (CollectionUtils.isEmpty(activeUserList)) {
      logger.info("no active user found...");
      return;
    }
    for (UserEntity activeUser : activeUserList) {
      sendNotificationToUser(activeUser.getTwitterId());
    }
  }
}