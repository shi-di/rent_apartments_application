package com.example.rent_product.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "apartments_table")
@Data

public class ApartmentsEntity {

    @Id
    @SequenceGenerator(name = "apartments_tableSequence", sequenceName = "apartments_table_sequence", allocationSize = 1, initialValue = 11)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartments_tableSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "room_amount")
    private String roomAmount;

    @Column(name = "price")
    private Integer price;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "global_rating")
    private Integer globalRating;

    @Column(name = "booking_to")
    private LocalDateTime bookingTo;

    @Column(name = "date_lot_registration")
    private LocalDateTime dateLotRegistration;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "apartmentsEntity")
    private AddressEntity addressEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "apartmentsEntity")
    private List<RatingEntity> ratingEntity;

}
