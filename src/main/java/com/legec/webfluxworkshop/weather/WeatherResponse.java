package com.legec.webfluxworkshop.weather;

class WeatherResponse {
    private final Integer humidity;
    private final Float maximumTemperature;
    private final Float minimumTemperature;
    private final Integer airPressure;
    private final Float windSpeed;
    private final Float windDirection;

    WeatherResponse(WeatherApi.Weather weather) {
        this.humidity = weather.getHumidity();
        this.maximumTemperature = weather.getMaximumTemperature();
        this.minimumTemperature = weather.getMinimumTemperature();
        this.airPressure = weather.getAirPressure();
        this.windSpeed = weather.getWindSpeed();
        this.windDirection = weather.getWindDirection();
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Float getMaximumTemperature() {
        return maximumTemperature;
    }

    public Float getMinimumTemperature() {
        return minimumTemperature;
    }

    public Integer getAirPressure() {
        return airPressure;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public Float getWindDirection() {
        return windDirection;
    }
}
