package com.example.rent_basic_apartment.repository;

import com.example.rent_basic_apartment.model.entity.AddressEntity;
import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    public List<AddressEntity> getAddressEntitiesByCity(String city);

    @Query(value = "SELECT a FROM AddressEntity a WHERE a.city = :city and a.apartmentsEntity.roomAmount = :roomsCount")
    public List<AddressEntity> getCityByParam(String city, String roomsCount);

    @Query(value = "SELECT a FROM AddressEntity a WHERE a.city = :city ORDER BY a.apartmentsEntity.price DESC limit 1")
    public List<AddressEntity> getApartmentsMinSum(String city);

}
