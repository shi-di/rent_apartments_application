package com.example.rent_product.controller;

import com.example.rent_product.service.BookingApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.rent_product.constant.ConstApplication.PRODUCT_DISCOUNT;

@RestController
@RequiredArgsConstructor
public class DiscountsController {


    private final BookingApartmentService apartmentService;

    /**
     * предоставление пользователю скидку на бронирование апартаментов
     */
    @PostMapping(PRODUCT_DISCOUNT)
    public void getProductDiscount(@RequestParam Long id) {

        apartmentService.productDiscount(id);
    }
}
