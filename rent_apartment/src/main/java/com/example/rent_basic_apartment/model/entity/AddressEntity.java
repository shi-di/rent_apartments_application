package com.example.rent_basic_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "address_table")
@Entity
@Data
public class AddressEntity {

    @Id
    @SequenceGenerator(name = "address_tableSequence", sequenceName = "address_table_sequence", allocationSize = 1, initialValue = 11)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_tableSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "apartments_number")
    private String apartmentsNumber;

    @OneToOne()
    @JoinColumn(name = "id_apartment")
    private ApartmentsEntity apartmentsEntity;
}

