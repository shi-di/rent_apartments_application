package com.example.rent_basic_apartment.model.dto;

import lombok.Data;

import java.util.List;

import static com.example.rent_basic_apartment.constant.ConstApplication.AVAILABLE_LIST_OF_APARTMENTS;

@Data
public class FullInfoResponse extends ApplicationException {
    private String weather;
    private String value = AVAILABLE_LIST_OF_APARTMENTS;
    private List<FullInfoByApartments> apartments;

}
