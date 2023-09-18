package com.example.rent_basic_apartment.service;


import com.example.rent_basic_apartment.mapper.ApplicationMapper;
import com.example.rent_basic_apartment.model.dto.RatingApartmentDto;
import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import com.example.rent_basic_apartment.model.entity.RatingEntity;
import com.example.rent_basic_apartment.repository.ApartmentsRepository;
import com.example.rent_basic_apartment.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.rent_basic_apartment.constant.ConstApplication.COMMENT_SAVE;


@Service
@RequiredArgsConstructor
public class RatingGlobalServiceImpl implements RatingGlobalService {
    private final Logger logger = LoggerFactory.getLogger(RatingGlobalServiceImpl.class);

    private final RatingRepository ratingRepository;
    private final ApartmentsRepository apartmentsRepository;
    private final  ApplicationMapper applicationMapper;

    /**
     * сохранение рейтинга в БД rating_table
     */
    @Override
    public String saveRatingApartments(RatingApartmentDto ratingApartmentDto) {

        logger.info("RatingGlobalServiceImpl -> saveRatingApartments()");

        RatingEntity ratingEntity = applicationMapper.prepareRatingEntity(ratingApartmentDto);
        ApartmentsEntity apartments = apartmentsRepository.findById(ratingApartmentDto.getApartmentId()).get();

        ratingEntity.setApartmentsEntity(apartments);
        ratingRepository.save(ratingEntity);

        updateGlobalRatingApartment(apartments, ratingApartmentDto);

        logger.info("RatingGlobalServiceImpl <- saveRatingApartments()");

        return COMMENT_SAVE;
    }

    /**
     * сохранение глобального рейтинга в БД apartments_table в колонку global_rating
     */
    private void updateGlobalRatingApartment(ApartmentsEntity apartments, RatingApartmentDto ratingApartmentDto) {

        logger.info("RatingGlobalServiceImpl -> updateGlobalRatingApartment()");

        List<RatingEntity> ratingList = ratingRepository.getRatingEntitysByApartmentsEntity_Id(ratingApartmentDto.getApartmentId());
        double ratingResult = ratingList.stream().mapToInt(RatingEntity::getRating).average().orElseThrow();
        apartments.setGlobalRating((int) ratingResult);

        apartmentsRepository.save(apartments);

        logger.info("RatingGlobalServiceImpl <- updateGlobalRatingApartment()");
    }
}
