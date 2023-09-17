package com.example.rent_basic_apartment.service;


import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import com.example.rent_basic_apartment.repository.ApartmentsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.rent_basic_apartment.constant.ConstApplication.APARTMENT_BOOKED;
import static com.example.rent_basic_apartment.constant.ConstApplication.APARTMENT_NOT_AVAILABLE;

@Service
@RequiredArgsConstructor
public class BookingApartmentServiceImpl implements BookingApartmentService {

    private final Logger logger = LoggerFactory.getLogger(BookingApartmentServiceImpl.class);

    private final ApartmentsRepository apartmentsRepository;
    private final PrepareToBookingService prepareToBookingService;

    /**
     * бронирование апартаментов
     */
    @Override
    public String bookingApartmentsAndPayment(Long bookingApartmentId,
                                              LocalDateTime startBookingDate,
                                              LocalDateTime endBookingDate) {
        logger.info("BookingApartmentServiceImpl -> bookingApartmentsAndPayment()");

        ApartmentsEntity apartmentsEntity = apartmentsRepository.findById(bookingApartmentId).get();
        if (!apartmentsEntity.getAvailable()) {
            return APARTMENT_NOT_AVAILABLE;
        }
        apartmentsEntity.setBookingTo(endBookingDate);
        apartmentsEntity.setAvailable(false);
        apartmentsRepository.save(apartmentsEntity);

        prepareToBookingService.writeToBookingHistory(apartmentsEntity, startBookingDate, endBookingDate);

        logger.info("BookingApartmentServiceImpl <- bookingApartmentsAndPayment()");

        return APARTMENT_BOOKED;
    }

}
