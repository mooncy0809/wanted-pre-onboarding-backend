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

    // 채용공고 수정
    public JobPosting updateJobPosting(Integer id, JobPostingDTO jobPostingDTO) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 채용공고 찾을 수 없음 : "+ id));

        jobPosting.setPosition(jobPostingDTO.getPosition());
        jobPosting.setCompensation(jobPostingDTO.getCompensation());
        jobPosting.setDetail(jobPostingDTO.getDetail());
        jobPosting.setSkill(jobPostingDTO.getSkill());

        return jobPostingRepository.save(jobPosting);
    }

    // 채용공고 삭제
    public void deleteJobPosting(Integer id) {
        jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 채용공고 찾을 수 없음 : " + id));

        jobPostingRepository.deleteById(id);
    }
}
