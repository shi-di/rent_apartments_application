package com.example.rent_basic_apartment.geocode_manager;


import com.example.rent_basic_apartment.model.dto.city.ResponseCityInformation;
import com.example.rent_basic_apartment.model.entity.IntegrationServiceEntity;
import com.example.rent_basic_apartment.repository.IntegrationServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.rent_basic_apartment.constant.ConstApplication.LOCATION_SERVICE;
import static com.example.rent_basic_apartment.constant.ConstApplication.NO_INFO_FOR_YOUR_LOCATION;


@Service
public class GeocodeManager {
    private final Logger logger = LoggerFactory.getLogger(GeocodeManager.class);

    @Autowired
    private IntegrationServiceRepository integrationServiceRepository;

    /**
     * вычисление города по геолокации
     */
    public String getCityByLocation(String latitude, String longitude) {
        ResponseCityInformation fullInfoByPoint = getFullInfoByPoint(latitude, longitude);
        return getCity(fullInfoByPoint);
    }

    /**
     * получение полной информации по геолокации с подключением интеграции
     */
    private ResponseCityInformation getFullInfoByPoint(String latitude, String longitude) {

        logger.warn("GeocodeManager -> getFullInfoByPoint()");

        RestTemplate restTemplate = new RestTemplate();
        IntegrationServiceEntity cred = integrationServiceRepository.getGeoLocAppKeyEntityByServiceName(LOCATION_SERVICE);

        ResponseCityInformation body = restTemplate.exchange(String.format(cred.getUrlLocationInfo()
                        , latitude, longitude, cred.getApplicationKey()),
                HttpMethod.GET,
                new HttpEntity<>(null),
                ResponseCityInformation.class).getBody();

        logger.warn("GeocodeManager <- getFullInfoByPoint()");

        return body;
    }

    /**
     * получаем город из json
     */
    private String getCity(ResponseCityInformation value) {
        if (!value.getResultList().isEmpty()) {
            if (value.getResultList().get(0) != null) {
                if (value.getResultList().get(0).getComponents() != null) {
                    if (value.getResultList().get(0).getComponents().getCityName() != null) {
                        return value.getResultList().get(0).getComponents().getCityName();
                    } else if (value.getResultList().get(0).getComponents().getTownName() != null) {
                        return value.getResultList().get(0).getComponents().getTownName();
                    } else if (value.getResultList().get(0).getComponents().getStateName() != null) {
                        return value.getResultList().get(0).getComponents().getStateName();
                    } else {
                        return NO_INFO_FOR_YOUR_LOCATION;
                    }

                }
            }
        }
        return null;
    }
}
