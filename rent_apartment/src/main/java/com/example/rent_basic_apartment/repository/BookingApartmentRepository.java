package com.example.rent_basic_apartment.repository;


import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingApartmentRepository extends JpaRepository<ApartmentsEntity, Long> {
    public ApartmentsEntity getApartmentsEntityById(Long id);
}
