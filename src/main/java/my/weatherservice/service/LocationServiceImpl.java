/*
 * Copyright (c) 2002-2022 meteo@m4j.ru
 */
package my.weatherservice.service;

import java.util.Collections;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import my.weatherservice.providers.ya.model.LocationDto;
import my.weatherservice.providers.ya.model.LocationsDto;

@Service
public class LocationServiceImpl implements LocationService {

    private final ResourceLoader resourceLoader;

    public LocationServiceImpl(ResourceLoader resourceLoader) {
        super();
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<LocationDto> requestLocations() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            final Resource resource = resourceLoader.getResource("classpath:locations.xml");
            LocationsDto locations = xmlMapper.readValue(resource.getInputStream(), LocationsDto.class);
            return locations.getLocations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
