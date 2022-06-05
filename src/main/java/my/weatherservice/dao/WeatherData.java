package my.weatherservice.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @ElementCollection
    @CollectionTable(name = "WEATHER_DATA_TEMPERATURE", joinColumns = @JoinColumn(name = "weather_data_id"))
    private List<Double> temperature;

}
