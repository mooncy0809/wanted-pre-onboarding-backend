package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingDTO;
import com.wanted.wantedpreonboardingbackend.repository.JobPostingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobPostingService {
    @Autowired
    private JobPostingRepository jobPostingRepository;

    // 채용공고 등록
    public JobPosting createJobPosting(JobPostingDTO jobPostingDTO) {
        JobPosting jobPosting = new JobPosting();
        BeanUtils.copyProperties(jobPostingDTO, jobPosting);
        return jobPostingRepository.save(jobPosting);
    }
}
