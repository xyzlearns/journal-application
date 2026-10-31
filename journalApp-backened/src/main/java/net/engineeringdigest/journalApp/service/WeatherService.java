package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.responce.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apiKey="df6aaddab022f867b82a4b0bbafdc436";
    private static final String API="https://api.weatherstack.com/current?access_key=df6aaddab022f867b82a4b0bbafdc436&query=Mumbai";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather (String city){
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(API, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }


}
