package com.example.rent_basic_apartment.controller;

import com.example.rent_basic_apartment.model.dto.AuthorizationCredation;
import com.example.rent_basic_apartment.model.dto.RegistrationNewUserDto;
import com.example.rent_basic_apartment.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static com.example.rent_basic_apartment.constant.ConstApplication.AUTHORIZATION_USER;
import static com.example.rent_basic_apartment.constant.ConstApplication.REGISTRATION_NEW_USER;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    /**
     * регистрация нового пользователя
     */
    @PostMapping(REGISTRATION_NEW_USER)
    public String registrationUser(@RequestBody RegistrationNewUserDto client) {
        return registrationService.registrationNewUser(client);
    }
    /**
     * авторизация пользователя
     */
    @PostMapping(AUTHORIZATION_USER)
    public String authorizationUser(@RequestBody AuthorizationCredation authorizationClient) {
        return registrationService.authorizationUser(authorizationClient);
    }

}
