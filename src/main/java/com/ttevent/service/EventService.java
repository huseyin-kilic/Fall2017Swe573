/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import io.swagger.client.ApiException;
import io.swagger.client.api.KategoriServisiApi;
import io.swagger.client.api.SehirServisiApi;
import io.swagger.client.model.Kategori;
import io.swagger.client.model.Sehir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huseyin.kilic
 */
@Service
public class EventService {

  @Autowired
  private SehirServisiApi locationEndPoint;

  @Autowired
  private KategoriServisiApi categoryEndPoint;

  public List<Sehir> getLocations() throws ApiException {
    return locationEndPoint.sehirlerGet();
  }

  public List<Kategori> getCategories() throws ApiException {
    return categoryEndPoint.kategorilerGet();
  }

}