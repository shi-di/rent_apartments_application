package com.example.rent_basic_apartment.mapper;


import com.example.rent_basic_apartment.model.dto.*;
import com.example.rent_basic_apartment.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    public ApartmentsEntity prepareApartmentsEntity(RegistrationFormApartmentsDto apartmentsDto);
    public AddressEntity prepareAddressEntity(RegistrationFormApartmentsDto apartmentsDto);
    @Mapping(target = "cardNumber", source = "numberCard")
    public ClientEntity prepareClientEntity(RegistrationNewUserDto newUserDto);
    public RatingEntity prepareRatingEntity(RatingApartmentDto ratingApartmentDto);
    public FullInfoByApartments entityTwoApartmentsDto(AddressEntity address, ApartmentsEntity apartments);
}
