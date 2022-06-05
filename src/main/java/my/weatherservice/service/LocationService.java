/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package my.weatherservice.service;

import java.util.List;

import my.weatherservice.providers.ya.model.LocationDto;

public interface LocationService {

    List<LocationDto> requestLocations();

}
