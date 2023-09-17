package com.example.rent_basic_apartment.repository;

import com.example.rent_basic_apartment.model.entity.BookingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistoryEntity, Long> {

}
