package com.example.rent_product.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "client_table")
@Data
public class ClientEntity {

    @Id
    @SequenceGenerator(name = "client_tableSequence", sequenceName = "client_table_sequence", allocationSize = 1, initialValue = 11)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_tableSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "user_nick_name")
    private String userNickName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "data_registration")
    private LocalDateTime dateRegistration;

    @Column(name = "parent_city")
    private String parentCity;

    @Column(name = "using_count")
    private Integer usingCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_product")
    private ProductEntity firstProduct;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_product")
    private ProductEntity secondProduct;

    public void setDateRegistration() {
        this.dateRegistration = LocalDateTime.now();
    }
}
