package com.legec.webfluxworkshop.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
class WeatherService {
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherApi weatherApi;
    private final WeatherRepository weatherRepository;

    WeatherService(WeatherApi weatherApi, WeatherRepository weatherRepository) {
        this.weatherApi = weatherApi;
        this.weatherRepository = weatherRepository;
    }

    Mono<WeatherResponse> weatherForLocation(String location, LocalDate date) {
        // TODO: fetch weather from DB, if not present call API, store copy in db and return
        return weatherFromApi(location, date)
                .map(WeatherResponse::new);
    }

    private Mono<Weather> weatherFromDb(String location, LocalDate date) {
        return Mono.empty();
    }

    private Mono<WeatherApi.Weather> weatherFromApi(String location, LocalDate date) {
        return weatherApi.findLocation(location)
                .filter(l -> l.getLocation().equalsIgnoreCase(location))
                .next()
                .flatMap(l -> weatherApi.weatherForLocation(l.getId()))
                .flatMapMany(l -> Flux.fromIterable(l.getConsolidatedWeather()))
                .filter(w -> w.getDate().isEqual(date))
                .next()
                .doOnNext(v -> log.info("Weather from API"));
    }
}
