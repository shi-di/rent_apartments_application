package com.example.rent_basic_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rating_table")
@Data
public class RatingEntity {

    @Id
    @SequenceGenerator(name = "rating_tableSequence", sequenceName = "rating_table_sequence", allocationSize = 1, initialValue = 11)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_tableSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "id_apartment")
    private ApartmentsEntity apartmentsEntity;

}
