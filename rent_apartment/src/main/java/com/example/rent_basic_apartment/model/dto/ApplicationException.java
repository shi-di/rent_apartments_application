package com.example.rent_basic_apartment.model.dto;

import lombok.Data;

@Data
public class ApplicationException {

    private String errorMassage;
    private String nullPointerMassage;

}
