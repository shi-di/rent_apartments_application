package com.example.rent_product.controller;

import com.example.rent_product.service.BookingApartmentService;
import com.example.rent_product.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.rent_product.constant.ConstApplication.PRODUCT_DISCOUNT;

@RestController
public class DiscountsController {

    @Autowired
    private BookingApartmentService apartmentService;
//    @Autowired
//    private EmailSenderService emailSenderService;

    /**
     * предоставление пользователю скидку на бронирование апартаментов
     */
    @PostMapping(PRODUCT_DISCOUNT)
    public void getProductDiscount(@RequestParam Long id) {

        apartmentService.productDiscount(id);
    }
//    @GetMapping("/test-mail")
//    public void get(@RequestParam Long id) {
//
//        emailSenderService.sendEmailToClient(id);
//    }
}
