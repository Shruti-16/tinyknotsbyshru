package com.tinyknotsbyshru.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    private String city;
    private String state;
    @Column(name = "pin_code")
    private String pinCode;
    private String country;
    @Column(name = "is_default")
    private Boolean isDefault;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
