package com.example.rent_basic_apartment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserGeoLoc {

    private String latitude;
    private String longitude;
}
