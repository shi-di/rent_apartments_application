package com.example.rent_basic_apartment.service;


import com.example.rent_basic_apartment.mapper.ApplicationMapper;
import com.example.rent_basic_apartment.model.dto.AuthorizationCredation;
import com.example.rent_basic_apartment.model.dto.RegistrationNewUserDto;
import com.example.rent_basic_apartment.model.dto.UserSession;
import com.example.rent_basic_apartment.model.entity.ClientEntity;
import com.example.rent_basic_apartment.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.rent_basic_apartment.constant.ConstApplication.*;
import static com.example.rent_basic_apartment.service.Base64Service.decoding;
import static com.example.rent_basic_apartment.service.Base64Service.encoding;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private final ClientRepository clientRepository;
    private final ApplicationMapper mapper;
    private final UserSession session;

    /**
     * регистрация нового пользователя
     */
    @Override
    public String registrationNewUser(RegistrationNewUserDto registrationNewUserDto) {

        logger.info("RegistrationServiceImpl -> registrationNewUser()");

        ClientEntity client = mapper.prepareClientEntity(registrationNewUserDto);
        if (clientRepository.getClientEntityByUserEmail(client.getUserEmail()) != null) {
            return USER_NAME_IS_BUSY;
        }
        logger.warn("RegistrationServiceImpl <- registrationNewUser()");
        client.setUserPassword(encoding(client.getUserPassword()));
        client.setCardNumber(encoding(client.getCardNumber()));
        client.setParentCity(client.getParentCity());
        client.setUsingCount(0);
        client.setDateRegistration();
        clientRepository.save(client);

        logger.info("RegistrationServiceImpl <- registrationNewUser()");

        return REGISTERED_SIGN_IN;
    }

    /**
     * авторизация пользователя
     */
    @Override
    public String authorizationUser(AuthorizationCredation authorizationCredation) {

        logger.info("RegistrationServiceImpl -> authorizationUser()");


        ClientEntity clientEntityByUserEmail = clientRepository.getClientEntityByUserEmail(authorizationCredation.getUserEmail());
        if (clientEntityByUserEmail == null) {
            return USER_NOT_REGISTERED;
        }

        String userPassword = authorizationCredation.getUserPassword();
        if (userPassword.equals(decoding(clientEntityByUserEmail.getUserPassword()))) {
            session.setUserEmail(clientEntityByUserEmail.getUserEmail());
            session.setUserNickName(clientEntityByUserEmail.getUserNickName());

            logger.info("RegistrationServiceImpl <- authorizationUser()");

            return WELCOME;
        }

        return INCORRECT_PASSWORD;
    }

}
