package com.example.rent_product.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_table")
@Data
public class ProductEntity {

    @Id
    @SequenceGenerator(name = "product_tableSequence", sequenceName = "product_table_sequence", allocationSize = 1, initialValue = 11)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_tableSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "offer")
    private String offer;

    @Column(name = "status_active_inactive")
    private Boolean statusActiveInactive;

    @Column(name = "discount_amount")
    private Integer discountAmount;

    @Column(name = "seasonality_of_offers")
    private String seasonalityOfOffers;

}
