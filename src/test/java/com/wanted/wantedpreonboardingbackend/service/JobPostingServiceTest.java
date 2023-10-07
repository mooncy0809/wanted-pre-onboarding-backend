package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingDTO;
import com.wanted.wantedpreonboardingbackend.repository.JobPostingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class JobPostingServiceTest {
    @Autowired
    private JobPostingService jobPostingService;

    @Mock
    private JobPostingRepository jobPostingRepository;

    @DisplayName("createJobPosting: 채용공고 등록 성공.")
    @Test
    void testCreateJobPosting() {
        JobPostingDTO jobPostingDTO = new JobPostingDTO();
        jobPostingDTO.setCompanyId(1);
        jobPostingDTO.setCompensation(1000000);
        jobPostingDTO.setPosition("백엔드 주니어 개발자");
        jobPostingDTO.setDetail("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        jobPostingDTO.setSkill("Python");

        var createJobPosting = jobPostingService.createJobPosting(jobPostingDTO);

        assertNotNull(createJobPosting);
        assertEquals("백엔드 주니어 개발자", createJobPosting.getPosition());
    }

    @DisplayName("updateJobPosting: 채용공고 수정 성공.")
    @Test
    void testUpdateJobPosting() {
        JobPostingDTO jobPostingDTO = new JobPostingDTO();
        jobPostingDTO.setCompensation(2000000);
        jobPostingDTO.setPosition("백엔드 시니어 개발자");
        jobPostingDTO.setDetail("원티드랩에서 시니어 개발자를 채용합니다. 자격요건은..");
        jobPostingDTO.setSkill("Java");

        Integer id = 6; //수정할 채용공고_id
        JobPosting jobPosting = new JobPosting();
        when(jobPostingRepository.findById(id)).thenReturn(Optional.of(jobPosting));

        JobPosting updateJobPosting = jobPostingService.updateJobPosting(id, jobPostingDTO);

        assertNotNull(updateJobPosting);
        assertEquals(jobPostingDTO.getPosition(), updateJobPosting.getPosition());
    }

    @DisplayName("deleteJobPosting: 채용공고 삭제 성공.")
    @Test
    void testDeleteJobPosting() {
        Integer id = 1; // 삭제할 채용공고_id

        JobPosting jobPosting = new JobPosting();
        when(jobPostingRepository.findById(id)).thenReturn(Optional.of(jobPosting));

        jobPostingService.deleteJobPosting(id);

        Optional<JobPosting> jobPostingAfterDelete = jobPostingRepository.findById(id);
        assertNull(jobPostingAfterDelete.orElse(null).getId());
    }

    @DisplayName("getJobPostingsList: 채용공고 리스트 가져오기 성공.")
    @Test
    void testGetJobPostingsList() {
        JobPostingDTO jobPostingDTO = new JobPostingDTO();
        jobPostingDTO.setCompanyId(1);
        jobPostingDTO.setCompensation(1000000);
        jobPostingDTO.setPosition("백엔드 주니어 개발자");
        jobPostingDTO.setDetail("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        jobPostingDTO.setSkill("Python");

        JobPosting jobPosting = jobPostingService.createJobPosting(jobPostingDTO);

        List<JobPostingDTO> jobPostingDTOList = jobPostingService.getJobPostingsList();

        assertThat(jobPostingDTOList).isNotEmpty();
        assertThat(jobPostingDTOList).extracting(JobPostingDTO::getCompanyId)
                .contains(jobPosting.getCompany().getId());
    }

    @DisplayName("searchJobPosting: 채용공고 리스트 검색 성공.")
    @Test
    void testSearchJobPosting() {
        JobPostingDTO jobPostingDTO = new JobPostingDTO();
        jobPostingDTO.setCompanyId(1);
        jobPostingDTO.setCompensation(1000000);
        jobPostingDTO.setPosition("백엔드 주니어 개발자");
        jobPostingDTO.setDetail("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        jobPostingDTO.setSkill("Python");

        JobPosting jobPosting = jobPostingService.createJobPosting(jobPostingDTO);

        List<JobPostingDTO> searchResult = jobPostingService.searchJobPosting("원티드");

        assertThat(searchResult).isNotEmpty();
        assertThat(searchResult).extracting(JobPostingDTO::getCompanyId)
                .contains(jobPosting.getCompany().getId());
    }
}