package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.dto.JobPostingDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobPostingServiceTest {
    @Autowired
    private JobPostingService jobPostingService;

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
}