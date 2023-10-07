package com.wanted.wantedpreonboardingbackend.repository;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
    List<JobPosting> findByCompanyId(Integer companyId);
    List<JobPosting> findSearchKeyword(String companyName, String position);

}
