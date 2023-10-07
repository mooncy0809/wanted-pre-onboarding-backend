package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.Company;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingDTO;
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
    public List<JobPostingDTO> getJobPostingsList() {
        List<JobPosting> jobPostings = jobPostingRepository.findAll();
        List<JobPostingDTO> jobPostingDTOList = new ArrayList<>();

        for(JobPosting jobPosting : jobPostings) {
            Integer companyId = jobPosting.getCompany().getId();
            Optional<Company> optionalCompany = companyRepository.findById(companyId);

            optionalCompany.ifPresent(company -> {
                JobPostingDTO jobPostingDTO = new JobPostingDTO();
                BeanUtils.copyProperties(jobPosting, jobPostingDTO);
                jobPostingDTO.setCompanyId(companyId);
                jobPostingDTO.setCompanyName(company.getName());
                jobPostingDTO.setCountry(company.getCountry());
                jobPostingDTO.setLocation(company.getLocation());

                List<Integer> jobPostingIdList = jobPostingRepository.findByCompanyId(companyId) // 회사의 다른 채용공고 목록 가져옴
                        .stream()
                        .map(JobPosting::getId) // 현재 채용공고를 제외한 다른 채용공고 필터링
                        .filter(id -> !id.equals(jobPosting.getId()))// JobPosting 객체의 getId 메서드 추출
                        .collect(Collectors.toList());

                jobPostingDTO.setOtherJobPostingids(jobPostingIdList);

                jobPostingDTOList.add(jobPostingDTO);
            });
        }
        return jobPostingDTOList;
    }

    // 채용공고 검색하기
    public List<JobPostingDTO> searchJobPosting(String keyword) {
        List<JobPosting> jobPostingList =
                jobPostingRepository.findByPositionContainingOrSkillContainingOrCompanyId_NameContainingOrCompanyId_CountryContainingOrCompanyId_LocationContaining
                        (keyword, keyword, keyword, keyword, keyword);
        List<JobPostingDTO> jobPostingDTOList = new ArrayList<>();

        for (JobPosting jobPosting : jobPostingList) {
            Integer companyId = jobPosting.getCompany().getId();
            Optional<Company> optionalCompany = companyRepository.findById(companyId);

            optionalCompany.ifPresent(company -> {
                JobPostingDTO jobPostingDTO = new JobPostingDTO();
                BeanUtils.copyProperties(jobPosting, jobPostingDTO);
                jobPostingDTO.setCompanyId(companyId);
                jobPostingDTO.setCompanyName(company.getName());
                jobPostingDTO.setCountry(company.getCountry());
                jobPostingDTO.setLocation(company.getLocation());

                List<Integer> jobPostingIdList = jobPostingRepository.findByCompanyId(companyId)
                        .stream()
                        .map(JobPosting::getId)
                        .filter(id -> !id.equals(jobPosting.getId()))
                        .collect(Collectors.toList());

                jobPostingDTO.setOtherJobPostingids(jobPostingIdList);

                jobPostingDTOList.add(jobPostingDTO);
            });
        }
        return jobPostingDTOList;
    }
}
