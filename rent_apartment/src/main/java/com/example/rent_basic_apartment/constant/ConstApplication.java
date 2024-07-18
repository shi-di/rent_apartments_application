package com.example.rent_basic_apartment.constant;

public class ConstApplication {

    public static final String BASE_URL = "/application";
    public static final String REGISTRATION_NEW_USER = BASE_URL + "/registration_user";
    public static final String AUTHORIZATION_USER = BASE_URL + "/authorization_user";
    public static final String REGISTRATION_APARTMENT = "/registration-apartment";
    public static final String GET_APARTMENT = "/get-apartments";
    public static final String RATING_APARTMENT = BASE_URL + "/rating-apartments";
    public static final String BOOKING_APARTMENT = BASE_URL + "/booking-apartment";
    public static final String GET_REPORT = BASE_URL + "/get-report";
    public static final String LOCATION_SERVICE = "opencagedata.com";
    public static final String WEATHER_SERVICE = "weather.yandex.ru";
    public static final String PRODUCT_DISCOUNT_SERVICE = "http://localhost:9099/product-discount?id=%s";

    public static final String CITY_BY_POINT_ERROR = "Возникла ошибка сервиса, " +
            "место вашего расположения не определено, попробуйте попытку позже";

    public static final String AUTHORIZATION_INTEGRATION_ERROR = "Возникла ошибка авторизации на сервисе интеграции";
    public static final String NULL_POINTER_ERROR = "Ключ не может быть нулевым";
    public static final String AVAILABLE_LIST_OF_APARTMENTS = "Доступный список квартир: ";

    public static final String APARTMENT_BOOKED = "Апартаменты забронированы, спасибо что воспользовались нашим сервисом";
    public static final String APARTMENT_NOT_AVAILABLE = "Апартаменты на эти даты не доступны, попробуйте выбрать другие даты.";
    public static final String COMMENT_SAVE = "Ваш комментарий сохранён, спасибо за ваш отзыв";
    public static final String USER_NAME_IS_BUSY = "На электронную почту уже зарегистрирован пользователь, попробуйте другую почту";
    public static final String REGISTERED_SIGN_IN = "Пользователь зарегистрован, войдите в систему";
    public static final String SIGN_IN = "Войдите в систему";
    public static final String APARTMENTS_REGISTERED = "Апартаменты зарегистрированы";
    public static final String USER_NOT_REGISTERED = "Такой пользователь не зарегистрован";
    public static final String NO_INFO_FOR_YOUR_LOCATION = "Нет информации по вашему местоположению";
    public static final String IN_YOUR_REGION = "В вашем регионе ";
    public static final String AIR_TEMPERATURE = " температура воздуха ";
    public static final String WELCOME = "Добро пожаловать";
    public static final String INCORRECT_PASSWORD = "Не верный пороль";
}
