package com.wanted.wantedpreonboardingbackend.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "job_posting_id", nullable = false)
    private JobPosting jobPosting;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
