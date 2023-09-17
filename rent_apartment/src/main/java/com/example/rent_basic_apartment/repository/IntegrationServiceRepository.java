package com.example.rent_basic_apartment.repository;


import com.example.rent_basic_apartment.model.entity.IntegrationServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationServiceRepository extends JpaRepository<IntegrationServiceEntity, Long> {
        public IntegrationServiceEntity getGeoLocAppKeyEntityByServiceName(String serviceName);
        public IntegrationServiceEntity getIntegrationServiceEntityByUrlLocationInfo(String serviceName);
}
