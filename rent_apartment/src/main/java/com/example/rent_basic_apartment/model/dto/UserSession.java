package com.example.rent_basic_apartment.model.dto;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class UserSession {

    private String userNickName;
    private String userEmail;
}
