CREATE TABLE weather (
    id SERIAL PRIMARY KEY,
    location VARCHAR(255),
    `date` DATE,
    max_temperature FLOAT,
    min_temperature FLOAT,
    air_pressure INT,
    humidity INT,
    wind_speed FLOAT,
    wind_direction FLOAT
);

CREATE UNIQUE INDEX weather_location_date ON weather(location, `date`);
