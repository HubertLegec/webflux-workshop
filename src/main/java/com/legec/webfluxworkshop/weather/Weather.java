package com.legec.webfluxworkshop.weather;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

class Weather {
    @Id
    private Long id;
    private final String location;
    private final LocalDate date;
    private final Integer humidity;
    @Column("max_temperature")
    private final Float maximumTemperature;
    @Column("min_temperature")
    private final Float minimumTemperature;
    private final Integer airPressure;
    private final Float windSpeed;
    private final Float windDirection;

    Weather(String location, LocalDate date, Integer humidity, Float maximumTemperature,
            Float minimumTemperature, Integer airPressure, Float windSpeed, Float windDirection) {
        this.location = location;
        this.date = date;
        this.humidity = humidity;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.airPressure = airPressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getLocation() {
        return location;
    }

    LocalDate getDate() {
        return date;
    }

    Integer getHumidity() {
        return humidity;
    }

    Float getMaximumTemperature() {
        return maximumTemperature;
    }

    Float getMinimumTemperature() {
        return minimumTemperature;
    }

    Integer getAirPressure() {
        return airPressure;
    }

    Float getWindSpeed() {
        return windSpeed;
    }

    Float getWindDirection() {
        return windDirection;
    }
}
