package com.example.rent_basic_apartment.service;


import com.example.rent_basic_apartment.model.dto.FullInfoResponse;
import com.example.rent_basic_apartment.model.dto.RegistrationFormApartmentsDto;
import com.example.rent_basic_apartment.model.dto.UserGeoLoc;

public interface RentApartmentsService {

    public String saveToBase(RegistrationFormApartmentsDto apartmentsDto);
    public FullInfoResponse geoLocApartments(UserGeoLoc geoLoc);



}
