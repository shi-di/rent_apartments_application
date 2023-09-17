package com.example.rent_basic_apartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RentBasicApartmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentBasicApartmentApplication.class, args);
    }

}
