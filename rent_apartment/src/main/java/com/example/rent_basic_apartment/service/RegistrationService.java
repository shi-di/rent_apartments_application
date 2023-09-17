package com.example.rent_basic_apartment.service;


import com.example.rent_basic_apartment.model.dto.AuthorizationCredation;
import com.example.rent_basic_apartment.model.dto.RegistrationNewUserDto;

public interface RegistrationService {
    public String registrationNewUser(RegistrationNewUserDto registrationNewUserDto);
    public String authorizationUser(AuthorizationCredation authorizationCredation);

}
