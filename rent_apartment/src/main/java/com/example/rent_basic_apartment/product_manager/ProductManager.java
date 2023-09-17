package com.example.rent_basic_apartment.product_manager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.rent_basic_apartment.constant.ConstApplication.PRODUCT_DISCOUNT_SERVICE;

@Service
public class ProductManager {

    /**
     * интеграция в продуктовый сервис rent_product
     */
    public void getProductForBooking(Long id) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(String.format(PRODUCT_DISCOUNT_SERVICE, id),
                HttpMethod.POST,
                new HttpEntity<>(null, null),
                void.class);
    }


}
