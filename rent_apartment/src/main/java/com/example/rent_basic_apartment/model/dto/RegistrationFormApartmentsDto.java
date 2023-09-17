package com.example.rent_basic_apartment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationFormApartmentsDto {

    private String city;
    private String street;
    private String apartmentsNumber;
    private String roomAmount;
    private String price;


}