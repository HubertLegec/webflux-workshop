package com.legec.webfluxworkshop.weather;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("weather")
class WeatherController {
    private final WeatherService weatherService;

    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    Mono<WeatherResponse> weather(@RequestParam(name = "location") String location,
                                  @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return weatherService.weatherForLocation(location, date);
    }

    //TODO: get weather for city and date

}
