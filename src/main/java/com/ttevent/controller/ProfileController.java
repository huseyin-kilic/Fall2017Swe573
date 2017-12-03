/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.controller;

import com.ttevent.domain.UserProfile;
import io.swagger.client.ApiException;
import io.swagger.client.model.Etkinlik;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController {

  @RequestMapping
  public String profileView(Model model) throws ApiException {
    if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
      return "redirect:/login";
    }
    fillProfile(model);
    return "profile";
  }

  @RequestMapping("/{twitterId}")
  @ResponseBody
  public ResponseEntity<UserProfile> getProfile(@PathVariable("twitterId") Long twitterId) {
    UserProfile userProfile = profileService.getProfileByTwitterId(twitterId);
    if (userProfile.getId() == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(userProfile);
  }

  @RequestMapping("/{twitterId}/suggestedEvents")
  @ResponseBody
  public ResponseEntity<List<Etkinlik>> getSuggestedEvents(@PathVariable("twitterId") Long twitterId)
          throws ApiException {
    List<Etkinlik> suggestedEvents = eventService.searchEventsForUser(twitterId);
    if (CollectionUtils.isEmpty(suggestedEvents)) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(suggestedEvents);
  }

  @RequestMapping(method = RequestMethod.POST)
  public String saveProfile(@RequestBody MultiValueMap<String, Object> formData, Model model) throws ApiException {
    boolean saveSuccessful = profileService.saveProfile(formData);
    fillProfile(model);

    if (saveSuccessful) {
      return "redirect:/dashboard";
    } else {
      return "profile";
    }
  }


}