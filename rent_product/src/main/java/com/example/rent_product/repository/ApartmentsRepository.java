package com.example.rent_product.repository;

import com.example.rent_product.entity.ApartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ApartmentsRepository extends JpaRepository<ApartmentsEntity, Long> {

    public ApartmentsEntity getApartmentsEntityByDateLotRegistration(LocalDateTime date);
    public List<ApartmentsEntity> getApartmentsEntitiesByAvailableIsTrue();
}
