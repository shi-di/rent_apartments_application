package com.example.rent_basic_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "integration_service")
@Data
public class IntegrationServiceEntity {

    @Id
    @SequenceGenerator(name = "integration_serviceSequence", sequenceName = "integration_service_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "integration_serviceSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "url_location_info")
    private String urlLocationInfo;
    @Column(name = "application_key")
    private String applicationKey;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "key_name")
    private String keyName;

}
