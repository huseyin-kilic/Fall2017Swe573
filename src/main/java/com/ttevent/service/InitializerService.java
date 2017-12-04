/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.ttevent.dao.BaseDao;
import com.ttevent.domain.Category;
import com.ttevent.domain.Location;
import com.ttevent.entity.CategoryEntity;
import com.ttevent.entity.LocationEntity;
import com.ttevent.mapper.CategoryMapper;
import com.ttevent.mapper.LocationMapper;
import io.swagger.client.ApiException;
import io.swagger.client.model.Kategori;
import io.swagger.client.model.Sehir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Service
public class InitializerService {

  private static final Logger logger = LoggerFactory.getLogger(InitializerService.class);

  @Autowired
  private EventService eventService;

  @Autowired
  @Qualifier("base")
  private BaseDao dao;

  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private LocationMapper locationMapper;

  @Transactional
  public void fillReferenceData() throws ApiException {
    fillLocations();
    fillCategories();
  }

  private void fillLocations() throws ApiException {
    logger.info("retrieving locations from Events API...");
    List<Sehir> locations = eventService.getLocationsFromAPI();
    List<Location> locationList = new ArrayList<>();
    for (Sehir location : locations) {
      LocationEntity locationEntity =
              LocationEntity.builder()
                      .id(location.getId().longValue())
                      .adi(location.getAdi())
                      .radi(location.getRadi())
                      .build();
      dao.persist(locationEntity);
      locationList.add(locationMapper.convert(locationEntity));
    }
    eventService.setLocations(locationList);
    logger.info("locations retrieved successfully!");
  }

  private void fillCategories() throws ApiException {
    logger.info("retrieving categories from Events API...");
    List<Kategori> categories = eventService.getCategoriesFromAPI();
    List<Category> categoryList = new ArrayList<>();
    for (Kategori kategori : categories) {
      CategoryEntity categoryEntity =
              CategoryEntity.builder()
                      .id(kategori.getId().longValue())
                      .adi(kategori.getAdi())
                      .radi(kategori.getRadi())
                      .build();
      dao.persist(categoryEntity);
      categoryList.add(categoryMapper.convert(categoryEntity));
    }
    eventService.setCategories(categoryList);
    logger.info("categories retrieved successfully!");
  }

}