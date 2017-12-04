/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.ttevent.domain.UserProfile;
import io.swagger.client.model.Etkinlik;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Service
@Data
public class EmailService {

  private final String subject = "TTEvent Daily Update";

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private TemplateEngine templateEngine;

  @Value("${spring.mail.username}")
  private String emailFrom;

  @Value("${appRoot}")
  private String appRoot;

  public String build(List<Etkinlik> events, UserProfile userProfile) {
    Context context = new Context();
    context.setVariable("events", events);
    context.setVariable("userProfile", userProfile);
    context.setVariable("appRoot", appRoot);
    return templateEngine.process("mailTemplate", context);
  }

  public void send(String emailTo, List<Etkinlik> events, UserProfile userProfile) {
    MimeMessage mail = javaMailSender.createMimeMessage();
    try {
      String content = build(events, userProfile);

      MimeMessageHelper helper = new MimeMessageHelper(mail, true);
      helper.setTo(emailTo);
      helper.setFrom(emailFrom);
      helper.setSubject(subject);
      helper.setText(content, true);

      javaMailSender.send(mail);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}