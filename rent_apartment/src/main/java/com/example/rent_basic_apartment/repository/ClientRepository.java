package com.example.rent_basic_apartment.repository;


import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import com.example.rent_basic_apartment.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public ClientEntity getClientEntityByUserEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM client_table ORDER BY id DESC LIMIT 1")
    public ClientEntity getLastId();
}
