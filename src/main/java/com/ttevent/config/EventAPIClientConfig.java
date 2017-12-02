/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.config;

import io.swagger.client.ApiClient;
import io.swagger.client.api.EtkinlikServisiApi;
import io.swagger.client.api.KategoriServisiApi;
import io.swagger.client.api.SehirServisiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huseyin.kilic
 */
@Configuration
public class EventAPIClientConfig {

  @Autowired
  private ApiClient apiClient;

  @Bean
  public ApiClient getAPIClient() {
    ApiClient apiClient = new ApiClient();
    apiClient.setVerifyingSsl(false);
    apiClient.setApiKey("9e2d71da31916dd0d42c1b0f31bd78fc");
    return apiClient;
  }

  @Bean
  public SehirServisiApi getLocationsAPI() {
    SehirServisiApi sehirServisiApi = new SehirServisiApi();
    sehirServisiApi.setApiClient(apiClient);
    return sehirServisiApi;
  }

  @Bean
  public KategoriServisiApi getCategoriesAPI() {
    KategoriServisiApi kategoriServisiApi = new KategoriServisiApi();
    kategoriServisiApi.setApiClient(apiClient);
    return kategoriServisiApi;
  }

  @Bean
  public EtkinlikServisiApi getEventAPI() {
    EtkinlikServisiApi etkinlikServisiApi = new EtkinlikServisiApi();
    etkinlikServisiApi.setApiClient(apiClient);
    return etkinlikServisiApi;
  }
}
