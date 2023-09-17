package com.example.rent_basic_apartment.test_apartment_controller;

import com.example.rent_basic_apartment.model.dto.*;

import java.util.Base64;


public class PrepareTestInfo {

    public static final String CITY = "Moscow";
    public static final String ID = "10";
    public static final String END = "2023-10-20T14:00:00";
    public static final String START = "2023-09-05T13:00:00";
    public static final String WEATHER = "В вашем регионе пасмурно температура воздуха 19";

    public static UserGeoLoc prepareGeoLocObject() {
        return new UserGeoLoc("55.756875", "37.615641");
    }

    public static RegistrationNewUserDto prepareRegistrationUser() {
        return new RegistrationNewUserDto("OLYA777", "aOLYA888@mail.ru", "wegwhe",
                "1324 6312 9081 2341", "Москва");
    }

    public static AuthorizationCredation prepareAuthorizationUser() {

        return new AuthorizationCredation("anna@mail.ru", decoding("d2Vnd2hl"));
    }

    public static RegistrationFormApartmentsDto prepareRegistrationApartment() {

        return new RegistrationFormApartmentsDto("Moscow", "Mitinskaya",
                "12", "250", "3800");
    }

    public static RatingApartmentDto prepareCommentAndRating() {
        return new RatingApartmentDto(1L, 6, "Good");
    }

    public static String decoding(String value) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(value));
    }
}
