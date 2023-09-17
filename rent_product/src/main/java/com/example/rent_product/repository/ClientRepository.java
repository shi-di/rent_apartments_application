package com.example.rent_product.repository;

import com.example.rent_product.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public ClientEntity getClientEntityByUserEmail(String email);
    public List<ClientEntity> findAllByFirstProductIsNull();
    public ClientEntity getClientEntityById(Long id);

}
