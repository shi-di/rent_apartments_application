package com.example.rent_basic_apartment.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingInfoForProductsDto {

    private Long clientId;
    private Long apartmentId;
    private Long productId;
    private LocalDateTime startBookingDate;
    private LocalDateTime endBookingDate;
}
