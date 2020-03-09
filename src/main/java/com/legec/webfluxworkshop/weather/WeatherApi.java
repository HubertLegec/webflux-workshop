package com.legec.webfluxworkshop.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
class WeatherApi {
    private final WebClient webClient;

    WeatherApi(@Value("${weather-api.url}") String apiUrl) {
        this.webClient = WebClient.create(apiUrl);
    }

    Flux<Location> findLocation(String query) {
        return webClient.get()
                .uri(b -> b.path("/location/search/").queryParam("query", query).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Location.class);
    }

    Mono<WeatherForLocation> weatherForLocation(int id) {
        return webClient.get()
                .uri("/api/location/{woeid}/", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherForLocation.class);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Location {
        @JsonProperty("title")
        private String location;
        @JsonProperty("location_type")
        private String locationType;
        @JsonProperty("latt_long")
        private String latitudeLongitude;
        @JsonProperty("woeid")
        private int id;

        public Location() {
        }

        Location(String location, String locationType, String latitudeLongitude, int id) {
            this.location = location;
            this.locationType = locationType;
            this.latitudeLongitude = latitudeLongitude;
            this.id = id;
        }

        String getLocation() {
            return location;
        }

        String getLocationType() {
            return locationType;
        }

        float getLatitude() {
            return Float.parseFloat(latitudeLongitude.split(",")[0]);
        }

        float getLongitude() {
            return Float.parseFloat(latitudeLongitude.split(",")[1]);
        }

        int getId() {
            return id;
        }
    }

    static class Weather {
        @JsonProperty("weather_state_name")
        private String stateName;
        @JsonProperty("weather_state_abbr")
        private String stateAbbreviation;
        @JsonProperty("wind_direction_compass")
        private String windDirectionCompass;
        @JsonProperty("created")
        private String created;
        @JsonProperty("applicable_date")
        private String date;
        @JsonProperty("min_temp")
        private Float minimumTemperature;
        @JsonProperty("max_temp")
        private Float maximumTemperature;
        @JsonProperty("the_temp")
        private Float theTemperature;
        @JsonProperty("wind_speed")
        private Float windSpeed;
        @JsonProperty("wind_direction")
        private Float windDirection;
        @JsonProperty("air_pressure")
        private Integer airPressure;
        @JsonProperty("humidity")
        private Integer humidity;
        @JsonProperty("visibility")
        private Float visibility;
        @JsonProperty("predictability")
        private Integer predictability;

        Weather() {
        }

        Weather(String stateName, String stateAbbreviation, String windDirectionCompass, String created, String date,
                       Float minimumTemperature, Float maximumTemperature, Float theTemperature, Float windSpeed,
                       Float windDirection, Integer airPressure, Integer humidity, Float visibility, Integer predictability) {
            this.stateName = stateName;
            this.stateAbbreviation = stateAbbreviation;
            this.windDirectionCompass = windDirectionCompass;
            this.created = created;
            this.date = date;
            this.minimumTemperature = minimumTemperature;
            this.maximumTemperature = maximumTemperature;
            this.theTemperature = theTemperature;
            this.windSpeed = windSpeed;
            this.windDirection = windDirection;
            this.airPressure = airPressure;
            this.humidity = humidity;
            this.visibility = visibility;
            this.predictability = predictability;
        }

        String getStateName() {
            return stateName;
        }

        String getStateAbbreviation() {
            return stateAbbreviation;
        }

        String getWindDirectionCompass() {
            return windDirectionCompass;
        }

        Instant getCreated() {
            return Instant.parse(created);
        }

        LocalDate getDate() {
            return LocalDate.parse(date);
        }

        Float getMinimumTemperature() {
            return minimumTemperature;
        }

        Float getMaximumTemperature() {
            return maximumTemperature;
        }

        Float getTheTemperature() {
            return theTemperature;
        }

        Float getWindSpeed() {
            return windSpeed;
        }

        Float getWindDirection() {
            return windDirection;
        }

        Integer getAirPressure() {
            return airPressure;
        }

        Integer getHumidity() {
            return humidity;
        }

        Float getVisibility() {
            return visibility;
        }

        Integer getPredictability() {
            return predictability;
        }
    }

    static class WeatherForLocation extends Location {
        @JsonProperty("consolidated_weather")
        private List<Weather> consolidatedWeather = new ArrayList<>();

        WeatherForLocation() {
        }

        WeatherForLocation(String title, String locationType, String latitudeLongitude, int id, List<Weather> consolidatedWeather) {
            super(title, locationType, latitudeLongitude, id);
            this.consolidatedWeather = consolidatedWeather;
        }

        List<Weather> getConsolidatedWeather() {
            return consolidatedWeather;
        }
    }
}
