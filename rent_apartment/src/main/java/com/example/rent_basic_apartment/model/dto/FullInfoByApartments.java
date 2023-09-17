package com.example.rent_basic_apartment.model.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@ToString
public class FullInfoByApartments {

    private String city;
    private String street;
    private String apartmentsNumber;
    private String roomAmount;
    private String price;
    private Boolean available;
    private LocalDateTime dateLotRegistration;

}
