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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Service
public class EventService {

  private List<Location> locations;
  private List<Category> categories;

  @Autowired
  private SehirServisiApi locationEndPoint;

  @Autowired
  private KategoriServisiApi categoryEndPoint;

  @Autowired
  private EtkinlikServisiApi eventEndPoint;

  @Autowired
  private ProfileService profileService;

  public List<Sehir> getLocationsFromAPI() throws ApiException {
    return locationEndPoint.sehirlerGet();
  }

  public List<Kategori> getCategoriesFromAPI() throws ApiException {
    return categoryEndPoint.kategorilerGet();
  }

  public List<Location> getLocations() {
    return locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public Location getLocation(Object locationId) {
    for (Location location : locations) {
      if (locationId.toString().equals(location.getId().toString())) {
        return location;
      }
    }
    return null;
  }

  public Category getCategory(Object categoryId) {
    for (Category category : categories) {
      if (categoryId.toString().equals(category.getId().toString())) {
        return category;
      }
    }
    return null;
  }

  public List<Etkinlik> searchEventsForUser(long twitterProfileId) throws ApiException {
    List<Etkinlik> events = new ArrayList<>();
    try {
      UserProfile userProfile = profileService.getProfileByTwitterId(twitterProfileId);
      List<Category> preferredCategories = userProfile.getPreferredCategories();
      List<Location> preferredLocations = userProfile.getPreferredLocations();
      List<String> searchKeywords = userProfile.getSearchKeywords();

      for (Location location : preferredLocations) {
        for (Category category : preferredCategories) {
          events.addAll(
                  eventEndPoint.etkinliklerGet(null, category.getId().toString(),
                          null, location.getId().toString(), 1, 20).getKayitlar()
          );
        }
      }
      return events;
    } catch (Exception e) {
      return events;
    }
  }
}