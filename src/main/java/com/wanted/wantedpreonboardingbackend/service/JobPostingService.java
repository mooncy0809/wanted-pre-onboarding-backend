package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.Company;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingDTO;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingDetailDTO;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingListDTO;
import com.wanted.wantedpreonboardingbackend.repository.CompanyRepository;
import com.wanted.wantedpreonboardingbackend.repository.JobPostingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobPostingService {
    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // 채용공고 등록
    public JobPosting createJobPosting(JobPostingDTO jobPostingDTO) {
        JobPosting jobPosting = new JobPosting();
        BeanUtils.copyProperties(jobPostingDTO, jobPosting);

        Integer companyId = jobPostingDTO.getCompanyId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("해당 ID의  회사를 찾을 수 없음 : " + companyId));

        jobPosting.setCompany(company);

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

    // 채용공고 목록 가져오기
    public List<JobPostingListDTO> getJobPostingsList() {
        return jobPostingRepository.findAll()
                .stream().map(JobPostingListDTO::new)
                .toList();
    }

    // 채용공고 검색하기
    public List<JobPostingListDTO> searchJobPosting(String keyword) {
        return jobPostingRepository.findByPositionContainingOrSkillContainingOrCompanyId_NameContainingOrCompanyId_CountryContainingOrCompanyId_LocationContaining
                        (keyword, keyword, keyword, keyword, keyword)
                .stream().map(JobPostingListDTO::new)
                .toList();
    }

    public JobPostingDetailDTO getJobPostingById(Integer id) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 채용공고 찾을 수 없음 : " + id));

        List<Integer> otherJobPostingIds = jobPostingRepository.findByCompanyId(jobPosting.getCompany().getId())
                .stream()
                .map(JobPosting::getId)
                .filter(postingId -> !postingId.equals(id))
                .collect(Collectors.toList());

        return new JobPostingDetailDTO(jobPosting, otherJobPostingIds);
    }
}
