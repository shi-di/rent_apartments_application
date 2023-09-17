package com.example.rent_basic_apartment.service;

import java.time.LocalDateTime;

public interface BookingApartmentService {

    public String bookingApartmentsAndPayment(Long bookingApartmentId,
                                              LocalDateTime startBookingDate,
                                              LocalDateTime endBookingDate);
}
