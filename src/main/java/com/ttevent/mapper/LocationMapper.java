/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.mapper;

import com.ttevent.domain.Location;
import com.ttevent.entity.LocationEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Component
public class LocationMapper {

  public Location convert(LocationEntity entity) {
    return Location.builder().id(entity.getId()).adi(entity.getAdi()).radi(entity.getRadi()).build();
  }

  public LocationEntity convert(Location dto) {
    return LocationEntity.builder().id(dto.getId()).adi(dto.getAdi()).radi(dto.getRadi()).build();
  }

  public List<Location> convertToDtoList(List<LocationEntity> entityList) {
    List<Location> list = new ArrayList<>();
    if (CollectionUtils.isEmpty(entityList)) {
      return list;
    }
    for (LocationEntity entity : entityList) {
      list.add(convert(entity));
    }
    return list;
  }

  public List<LocationEntity> convertToEntityList(List<Location> dtoList) {
    List<LocationEntity> list = new ArrayList<>();
    if (CollectionUtils.isEmpty(dtoList)) {
      return list;
    }
    for (Location dto : dtoList) {
      list.add(convert(dto));
    }
    return list;
  }
}
