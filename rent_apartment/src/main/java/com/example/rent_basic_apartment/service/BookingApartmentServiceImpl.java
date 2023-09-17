package com.example.rent_basic_apartment.service;


import com.example.rent_basic_apartment.model.dto.UserSession;
import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import com.example.rent_basic_apartment.product_manager.ProductManager;
import com.example.rent_basic_apartment.repository.ApartmentsRepository;
import com.example.rent_basic_apartment.repository.BookingHistoryRepository;
import com.example.rent_basic_apartment.repository.ClientRepository;
import com.example.rent_basic_apartment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.rent_basic_apartment.constant.ConstApplication.*;

@Service
@RequiredArgsConstructor
public class BookingApartmentServiceImpl implements BookingApartmentService {

    private final Logger logger = LoggerFactory.getLogger(BookingApartmentServiceImpl.class);

    private final ApartmentsRepository apartmentsRepository;
//    private final ClientRepository clientRepository;
//    private final BookingHistoryRepository bookingHistoryRepository;
//    private final ProductRepository productRepository;
//    private final ProductManager productManager;
//    private final UserSession session;
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
