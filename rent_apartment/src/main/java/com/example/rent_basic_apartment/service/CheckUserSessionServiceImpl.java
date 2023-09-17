package com.example.rent_basic_apartment.service;

import com.example.rent_basic_apartment.model.dto.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.rent_basic_apartment.constant.ConstApplication.SIGN_IN;

@Service
public class CheckUserSessionServiceImpl implements CheckUserSessionService {

    @Autowired
    private UserSession session;

    /**
     * проверяем пользователя, находится ли он в сессии или нет
     */
    @Override
    public String checkSession() {
        if (session.getUserNickName() == null) {
            return SIGN_IN;
        } else {
            return null;
        }
    }
}
