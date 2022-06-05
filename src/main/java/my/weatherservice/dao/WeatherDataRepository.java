package my.weatherservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findByDate(LocalDate date);
    List<WeatherData> findByDateAndLocation_City(LocalDate date, String city);
    List<WeatherData> findByLocation_CityOrderByIdDesc(String city);
}
