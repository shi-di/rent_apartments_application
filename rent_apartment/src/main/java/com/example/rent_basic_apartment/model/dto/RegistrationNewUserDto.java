package com.example.rent_basic_apartment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationNewUserDto {

    private String userNickName;
    private String userEmail;
    private String userPassword;
    private String numberCard;
    private String parentCity;
}
