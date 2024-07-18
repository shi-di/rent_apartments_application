package com.example.rent_basic_apartment.test_apartment_controller;

import com.example.rent_basic_apartment.geocode_manager.GeocodeManager;
import com.example.rent_basic_apartment.model.dto.*;
import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import com.example.rent_basic_apartment.model.entity.ClientEntity;
import com.example.rent_basic_apartment.model.entity.RatingEntity;
import com.example.rent_basic_apartment.repository.ApartmentsRepository;
import com.example.rent_basic_apartment.repository.ClientRepository;
import com.example.rent_basic_apartment.repository.RatingRepository;
import com.example.rent_basic_apartment.service.CheckUserSessionService;
import com.example.rent_basic_apartment.service.PrepareToBookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static com.example.rent_basic_apartment.constant.ConstApplication.*;
import static com.example.rent_basic_apartment.test_apartment_controller.PrepareTestInfo.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TestApartmentController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CheckUserSessionService checkUserSessionService;
    @MockBean
    private GeocodeManager geocodeManager;
    @MockBean
    private PrepareToBookingService prepareToBookingService;
    @Autowired
    private ApartmentsRepository apartmentsRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void testGetApartmentByGeo() throws Exception {
        UserGeoLoc userGeoloc = prepareGeoLocObject();
        when(geocodeManager.getCityByLocation(userGeoloc.getLatitude(), userGeoloc.getLongitude())).thenReturn(CITY);

        mockMvc.perform(post(GET_APARTMENT)
                        .content(asJSONstring(userGeoloc))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.apartments").isArray())
                .andExpect(jsonPath("$.weather").value(WEATHER))
                .andExpect(jsonPath("$.apartments[0].city").value(CITY));

    }

    @Test
    public void testRegistrationUser() throws Exception {
        RegistrationNewUserDto registrationNewUserDto = prepareRegistrationUser();

        MvcResult mvcResult = mockMvc.perform(post(REGISTRATION_NEW_USER)
                        .content(asJSONstring(registrationNewUserDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().string(Matchers.notNullValue())).andReturn();

        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        String result = new String(contentAsByteArray, StandardCharsets.UTF_8);
        Assert.assertEquals(REGISTERED_SIGN_IN, result);

        clearClientDb();

    }

    @Test
    public void testAuthorizationUser() throws Exception {
        AuthorizationCredation authorizationCredation = prepareAuthorizationUser();

        MvcResult mvcResult = mockMvc.perform(post(AUTHORIZATION_USER)
                        .content(asJSONstring(authorizationCredation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        String result = new String(contentAsByteArray, StandardCharsets.UTF_8);
        Assert.assertEquals(WELCOME, result);
    }

    @Test
    public void testRegistrationApartment() throws Exception {
        RegistrationFormApartmentsDto registrationFormApartmentsDto = prepareRegistrationApartment();
        when(checkUserSessionService.checkSession()).thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(post(REGISTRATION_APARTMENT)
                        .content(asJSONstring(registrationFormApartmentsDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        String result = new String(contentAsByteArray, StandardCharsets.UTF_8);
        Assert.assertEquals(APARTMENTS_REGISTERED, result);

        clearApartmentsDb();
    }


    @Test
    public void testCommentAndRating() throws Exception {
        RatingApartmentDto ratingApartmentDto = prepareCommentAndRating();

        MvcResult mvcResult = mockMvc.perform(post(RATING_APARTMENT)
                        .content(asJSONstring(ratingApartmentDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        String result = new String(contentAsByteArray, StandardCharsets.UTF_8);
        Assert.assertEquals(COMMENT_SAVE, result);

        clearRatingDb();
    }

    @Test
    public void testBookingApartment() throws Exception {
        doNothing().when(prepareToBookingService).writeToBookingHistory(any(ApartmentsEntity.class),
                any(LocalDateTime.class), any(LocalDateTime.class));

        MvcResult mvcResult = mockMvc.perform(post(BOOKING_APARTMENT)
                        .param("id", ID)
                        .param("startBookingDate", START)
                        .param("endBookingDate", END))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        String result = new String(contentAsByteArray, StandardCharsets.UTF_8);
        Assert.assertEquals(APARTMENT_BOOKED, result);

        ApartmentsEntity apartmentsEntity = apartmentsRepository.findById(Long.parseLong(ID)).get();
        Assert.assertEquals(false, apartmentsEntity.getAvailable());

        apartmentsEntity.setBookingTo(null);
        apartmentsEntity.setAvailable(true);
        apartmentsRepository.save(apartmentsEntity);
    }

    private String asJSONstring(final Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void clearApartmentsDb() {
        ApartmentsEntity lastApartment = apartmentsRepository.getLastId();
        apartmentsRepository.deleteById(lastApartment.getId());
    }
    public void clearClientDb() {
        ClientEntity lastClient = clientRepository.getLastId();
        clientRepository.deleteById(lastClient.getId());
    }
    public void clearRatingDb() {
        RatingEntity lastRating = ratingRepository.getLastId();
        ratingRepository.deleteById(lastRating.getId());
    }
}
