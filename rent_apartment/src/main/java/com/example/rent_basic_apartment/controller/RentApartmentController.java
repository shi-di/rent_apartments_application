package com.example.rent_basic_apartment.controller;

import com.example.rent_basic_apartment.model.dto.FullInfoResponse;
import com.example.rent_basic_apartment.model.dto.RatingApartmentDto;
import com.example.rent_basic_apartment.model.dto.RegistrationFormApartmentsDto;
import com.example.rent_basic_apartment.model.dto.UserGeoLoc;
import com.example.rent_basic_apartment.service.BookingApartmentService;
import com.example.rent_basic_apartment.service.CheckUserSessionService;
import com.example.rent_basic_apartment.service.RatingGlobalService;
import com.example.rent_basic_apartment.service.RentApartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.example.rent_basic_apartment.constant.ConstApplication.*;


@RestController
public class RentApartmentController {

    @Autowired
    private RentApartmentsService rentApartmentsService;
    @Autowired
    private BookingApartmentService bookingApartmentService;
    @Autowired
    private RatingGlobalService ratingGlobalService;
    @Autowired
    private CheckUserSessionService checkUserSessionService;

    /**
     * регистрация апартаментов
     */
    @PostMapping(REGISTRATION_APARTMENT)
    public String addNewApartments(@RequestBody RegistrationFormApartmentsDto apartmentsModel) {
        String checkResult = checkUserSessionService.checkSession();
        if (checkResult != null) {
            return checkResult;
        }
        return rentApartmentsService.saveToBase(apartmentsModel);
    }

    /**
     * получение списка апартаментов по геолокации
     */
    @PostMapping(GET_APARTMENT)
    public FullInfoResponse getApartmentsByGeo(@RequestBody UserGeoLoc geoLoc) {
        return rentApartmentsService.geoLocApartments(geoLoc);
    }

    /**
     * глобальный рейтинг апартаментов
     */
    @PostMapping(RATING_APARTMENT)
    public String addNewCommentApartments(@RequestBody RatingApartmentDto ratingApartmentDto) {
        String checkResult = checkUserSessionService.checkSession();
        if (checkResult != null) {
            return checkResult;
        }
        return ratingGlobalService.saveRatingApartments(ratingApartmentDto);
    }

    /**
     * бронирование апартаментов
     * startBookingDate - дата заезда
     * endBookingDate - дата выезда
     */
    @PostMapping(BOOKING_APARTMENT)
    public String bookingApartment(@RequestParam Long id,
                                   @RequestParam LocalDateTime startBookingDate,
                                   @RequestParam LocalDateTime endBookingDate) {
        String checkResult = checkUserSessionService.checkSession();
        if (checkResult != null) {
            return checkResult;
        }
        return bookingApartmentService.bookingApartmentsAndPayment(id, startBookingDate, endBookingDate);
    }

}
