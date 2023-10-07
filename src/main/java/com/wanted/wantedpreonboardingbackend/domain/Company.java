package com.wanted.wantedpreonboardingbackend.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private  Integer id;

    @Column(nullable = false)
    private String name;

    private String country;
    private String location;
}
