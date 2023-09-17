package com.example.rent_basic_apartment.handler;


import com.example.rent_basic_apartment.model.dto.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import static com.example.rent_basic_apartment.constant.ConstApplication.*;

/**
 * обработка исключений
 */
@RestControllerAdvice
public class RentApartmentsExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> httpClientErrorExceptionHandler(HttpClientErrorException ex) {

        ApplicationException exception = new ApplicationException();
        exception.setErrorMassage(AUTHORIZATION_INTEGRATION_ERROR);
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<?> resourceAccessException(ResourceAccessException ex) {

        ApplicationException exception = new ApplicationException();
        exception.setErrorMassage(CITY_BY_POINT_ERROR);
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullPointerExceptionError() {

        ApplicationException exception = new ApplicationException();
        exception.setNullPointerMassage(NULL_POINTER_ERROR);
        return ResponseEntity.badRequest().body(exception);
    }
}
