package com.example.rent_basic_apartment.repository;

import com.example.rent_basic_apartment.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    public List<AddressEntity> getAddressEntitiesByCity(String city);

}
