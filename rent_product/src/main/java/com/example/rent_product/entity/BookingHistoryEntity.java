package com.example.rent_product.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingHistoryEntity {

    @Id
    @SequenceGenerator(name = "booking_historySequence", sequenceName = "booking_history_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_historySequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "start_booking_date")
    private LocalDateTime startBookingDate;

    @Column(name = "end_booking_date")
    private LocalDateTime endBookingDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private ApartmentsEntity apartmentsEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

}
