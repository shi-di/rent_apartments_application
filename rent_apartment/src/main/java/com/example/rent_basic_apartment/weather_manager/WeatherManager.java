package com.example.rent_basic_apartment.weather_manager;

import com.example.rent_basic_apartment.constant.WeatherConditionMapConstant;
import com.example.rent_basic_apartment.model.dto.weather.ResponseWeatherInformation;
import com.example.rent_basic_apartment.model.entity.IntegrationServiceEntity;
import com.example.rent_basic_apartment.repository.IntegrationServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.rent_basic_apartment.constant.ConstApplication.*;


@Service
public class WeatherManager {
    private final Logger logger = LoggerFactory.getLogger(WeatherManager.class);

    @Autowired
    private IntegrationServiceRepository integrationServiceRepository;

    public String getWeatherByLocation(String latitude, String longitude) {

        logger.warn("WeatherManager -> getWeatherByLocation()");

        ResponseWeatherInformation weatherInfo = getFullInfoByPointForWeather(latitude, longitude);
        String conditionRus = WeatherConditionMapConstant.getDescription(weatherInfo.getFact().getCondition());
        String fullWeatherInformationByLocation = IN_YOUR_REGION + conditionRus + AIR_TEMPERATURE
                + weatherInfo.getFact().getTemp();

        logger.warn("WeatherManager <- getWeatherByLocation()");

        return fullWeatherInformationByLocation;
    }
    private ResponseWeatherInformation getFullInfoByPointForWeather(String latitude, String longitude) {

        logger.warn("WeatherManager -> getFullInfoByPointForWeather()");

        RestTemplate restTemplate = new RestTemplate();
        IntegrationServiceEntity cred = integrationServiceRepository.getGeoLocAppKeyEntityByServiceName(WEATHER_SERVICE);

        HttpHeaders headers = new HttpHeaders();
        headers.set(cred.getKeyName(), cred.getApplicationKey());
        ResponseWeatherInformation body = restTemplate.exchange(String.format(cred.getUrlLocationInfo()
                        , latitude, longitude, cred.getApplicationKey()),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ResponseWeatherInformation.class).getBody();

        logger.warn("WeatherManager <- getFullInfoByPointForWeather()");

        return body;
    }
}
