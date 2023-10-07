package com.wanted.wantedpreonboardingbackend.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "jobposting")
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Integer id; //채용공고_id

    @Column(name = "company_id" , nullable = false)
    private Integer companyId; //회사_id

    @Column(nullable = false)
    private String position; //채용포지션

    @Column(nullable = false)
    private String detail; //채용내용

    private Integer compensation; //채용보상금
    private String skill; //사용기술
}
