package my.weatherservice.domain;

import my.weatherservice.providers.ya.model.LocationDto;

public class Converters {
	public static CountryAndCity fromLocation(LocationDto gn) {
		return new CountryAndCity(gn.getLat(), gn.getLon(),"RU", gn.getGeoname());
	}

	public static LocationDto toLocation(CountryAndCity dto) {
		return LocationDto.builder().lat(dto.getLat()).lon(dto.getLon()).build();
	}

}
