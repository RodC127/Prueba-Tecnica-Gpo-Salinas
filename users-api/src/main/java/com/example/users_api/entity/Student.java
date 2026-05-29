package com.example.users_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long controlNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String street;
        private String neighborhood;
        private String city;
    }
}