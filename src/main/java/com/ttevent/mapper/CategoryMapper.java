/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.mapper;

import com.ttevent.domain.Category;
import com.ttevent.entity.CategoryEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Component
public class CategoryMapper {

  public Category convert(CategoryEntity entity) {
    return Category.builder().id(entity.getId()).adi(entity.getAdi()).radi(entity.getRadi()).build();
  }

  public CategoryEntity convert(Category dto) {
    return CategoryEntity.builder().id(dto.getId()).adi(dto.getAdi()).radi(dto.getRadi()).build();
  }

  public List<Category> convertToDtoList(List<CategoryEntity> entityList) {
    List<Category> list = new ArrayList<>();
    if (CollectionUtils.isEmpty(entityList)) {
      return list;
    }
    for (CategoryEntity entity : entityList) {
      list.add(convert(entity));
    }
    return list;
  }

  public List<CategoryEntity> convertToEntityList(List<Category> dtoList) {
    List<CategoryEntity> list = new ArrayList<>();
    if (CollectionUtils.isEmpty(dtoList)) {
      return list;
    }
    for (Category dto : dtoList) {
      list.add(convert(dto));
    }
    return list;
  }
}
