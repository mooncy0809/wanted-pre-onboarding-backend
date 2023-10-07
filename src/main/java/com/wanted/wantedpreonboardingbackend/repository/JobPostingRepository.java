package com.wanted.wantedpreonboardingbackend.repository;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
}
