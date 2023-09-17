package com.example.rent_basic_apartment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingApartmentDto {

    private Long apartmentId;
    private Integer rating;
    private String comment;

}
