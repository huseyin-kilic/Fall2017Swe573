/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.service;

import com.ttevent.exception.InvalidCategoryException;
import com.ttevent.exception.InvalidEmailException;
import com.ttevent.exception.InvalidInputException;
import com.ttevent.exception.InvalidLocationException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Service
@Getter
@Setter
public class ValidationService {

  private List<Exception> validationErrors;

  public List<Exception> validateFormData(MultiValueMap<String, Object> formData) {
    setValidationErrors(new ArrayList<Exception>());
    if (formData.get("locations") != null && formData.get("locations").size() > 5) {
      validationErrors.add(new InvalidLocationException("You can choose at most 5 locations"));
    }
    if (formData.get("categories") != null && formData.get("categories").size() > 5) {
      validationErrors.add(new InvalidCategoryException("You can choose at most 5 categories"));
    }
    if (StringUtils.isEmpty(formData.get("email").toString().replace("[", "").replace("]", ""))) {
      validationErrors.add(new InvalidEmailException("Email address is mandatory"));
    }
    return validationErrors;
  }

   public <T extends InvalidInputException> String getValidationErrorsInString(Class<T> clazz) {
    if (CollectionUtils.isEmpty(validationErrors)) {
      return "";
    }
    StringBuilder base = new StringBuilder();
    for (Exception validationError: validationErrors) {
      if (clazz == validationError.getClass()) {
        base.append(validationError.getMessage()).append(System.lineSeparator());
      }
    }
    return base.toString();
  }

}