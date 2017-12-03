/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.ttevent.domain.Category;
import com.ttevent.domain.Location;
import com.ttevent.domain.UserProfile;
import io.swagger.client.ApiException;
import io.swagger.client.api.EtkinlikServisiApi;
import io.swagger.client.api.KategoriServisiApi;
import io.swagger.client.api.SehirServisiApi;
import io.swagger.client.model.Etkinlik;
import io.swagger.client.model.Kategori;
import io.swagger.client.model.Sehir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  public void send(String emailTo, String subject, String body) {
    MimeMessage mail = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mail, true);
      helper.setTo("hklc86@gmail.com");
      helper.setFrom("ttevent.notifier@gmail.com");
      helper.setSubject(subject);
      helper.setText(body);
    } catch (MessagingException e) {
      e.printStackTrace();
    } finally {}
    javaMailSender.send(mail);

  }
}