package com.example.rent_basic_apartment.repository;


import com.example.rent_basic_apartment.model.entity.ClientEntity;
import com.example.rent_basic_apartment.model.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    public List<RatingEntity> getRatingEntitysByApartmentsEntity_Id(Long id);
    @Query(nativeQuery = true, value = "SELECT  * FROM rating_table ORDER BY id DESC LIMIT 1")
    public RatingEntity getLastId();


}
