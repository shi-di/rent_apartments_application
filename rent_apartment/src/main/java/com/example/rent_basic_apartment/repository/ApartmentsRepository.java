package com.example.rent_basic_apartment.repository;


import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ApartmentsRepository extends JpaRepository<ApartmentsEntity, Long> {

    public ApartmentsEntity getApartmentsEntityByDateLotRegistration(LocalDateTime date);
    @Query(nativeQuery = true, value = "SELECT * FROM apartments_table ORDER BY id DESC LIMIT 1")
    public ApartmentsEntity getLastId();
    public List<ApartmentsEntity> getApartmentsEntitiesByAvailableIsTrue();
}
