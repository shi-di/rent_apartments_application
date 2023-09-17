package com.example.rent_basic_apartment.service;

import com.example.rent_basic_apartment.geocode_manager.GeocodeManager;
import com.example.rent_basic_apartment.mapper.ApplicationMapper;
import com.example.rent_basic_apartment.model.dto.FullInfoByApartments;
import com.example.rent_basic_apartment.model.dto.FullInfoResponse;
import com.example.rent_basic_apartment.model.dto.RegistrationFormApartmentsDto;
import com.example.rent_basic_apartment.model.dto.UserGeoLoc;
import com.example.rent_basic_apartment.model.entity.AddressEntity;
import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import com.example.rent_basic_apartment.repository.AddressRepository;
import com.example.rent_basic_apartment.repository.ApartmentsRepository;
import com.example.rent_basic_apartment.weather_manager.WeatherManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_basic_apartment.constant.ConstApplication.APARTMENTS_REGISTERED;

@Service
public class RentApartmentsServiceImpl implements RentApartmentsService {
    private final Logger logger = LoggerFactory.getLogger(RentApartmentsServiceImpl.class);

    @Autowired
    private GeocodeManager geocodeManager;
    @Autowired
    private WeatherManager weatherManager;
    @Autowired
    private ApplicationMapper apartmentsMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ApartmentsRepository apartmentsRepository;

    /**
     * сохранение апартаментов в БД
     */
    @Override
    public String saveToBase(RegistrationFormApartmentsDto apartmentsDto) {

        logger.info("RentApartmentsServiceImpl -> saveToBase()");

        ApartmentsEntity apartments = apartmentsMapper.prepareApartmentsEntity(apartmentsDto);
        LocalDateTime timeOperation = LocalDateTime.now();
        apartments.setDateLotRegistration(timeOperation);
        apartments.setAvailable(true);
        apartmentsRepository.save(apartments);

        AddressEntity address = apartmentsMapper.prepareAddressEntity(apartmentsDto);
        address.setApartmentsEntity(apartmentsRepository.getApartmentsEntityByDateLotRegistration(timeOperation));
        addressRepository.save(address);

        logger.info("RentApartmentsServiceImpl <- saveToBase()");

        return APARTMENTS_REGISTERED;

    }

    /**
     * выгрузка всех свободных апартаментов с прогнозом погоды по геолокации пользователя
     */
    @Override
    public FullInfoResponse geoLocApartments(UserGeoLoc geoLoc) {

        logger.info("RentApartmentsServiceImpl -> geoLocApartments()");
        logger.warn("RentApartmentsServiceImpl -> geoLocApartments()");

        List<AddressEntity> addressEntitiesByCity = new ArrayList<>();
        List<FullInfoByApartments> fullInfoByApartments = new ArrayList<>();
        FullInfoResponse fullInfoResponse = new FullInfoResponse();

        String cityByLocation = geocodeManager.getCityByLocation(geoLoc.getLatitude(), geoLoc.getLongitude());
        String weatherByLocation = weatherManager.getWeatherByLocation(geoLoc.getLatitude(), geoLoc.getLongitude());
        addressEntitiesByCity = addressRepository.getAddressEntitiesByCity(cityByLocation);

        for (AddressEntity address : addressEntitiesByCity) {
            FullInfoByApartments result = apartmentsMapper.entityTwoApartmentsDto(address, address.getApartmentsEntity());
            fullInfoByApartments.add(result);
        }
        logger.info("RentApartmentsServiceImpl <- geoLocApartments()");
        logger.warn("RentApartmentsServiceImpl <- geoLocApartments()");

        return prepareResponse(fullInfoByApartments, weatherByLocation);
    }

    /**
     * подготовка всех свободных апартаментов с прогнозом погоды по геолокации пользователя
     */
    private FullInfoResponse prepareResponse(List<FullInfoByApartments> fullInfoByApartments, String weatherByLocation) {

        logger.info("RentApartmentsServiceImpl -> prepareResponse()");

        FullInfoResponse response = new FullInfoResponse();
        List<FullInfoByApartments> sortedList = fullInfoByApartments.stream()
                .filter(f -> f.getAvailable() == null || f.getAvailable() != false).collect(Collectors.toList());

        response.setApartments(sortedList);
        response.setWeather(weatherByLocation);

        logger.info("RentApartmentsServiceImpl <- prepareResponse()");

        return response;
    }
}
