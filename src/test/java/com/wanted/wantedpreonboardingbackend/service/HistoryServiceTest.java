package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.domain.User;
import com.wanted.wantedpreonboardingbackend.dto.HistoryDTO;
import com.wanted.wantedpreonboardingbackend.exception.DuplicateException;
import com.wanted.wantedpreonboardingbackend.repository.HistoryRepository;
import com.wanted.wantedpreonboardingbackend.repository.JobPostingRepository;
import com.wanted.wantedpreonboardingbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class HistoryServiceTest {
    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private HistoryService historyService;

    @Test
    void testApplyToJob() {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setJobPostingId(1);
        historyDTO.setUserId(1);

        when(historyRepository.existsByJobPostingIdAndUserId(1, 1)).thenReturn(false);

        JobPosting jobPosting = new JobPosting();
        when(jobPostingRepository.findById(1)).thenReturn(java.util.Optional.of(jobPosting));
        User user = new User();
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));

        HistoryDTO savedHistoryDTO = historyService.applyToJob(historyDTO);

        assertNotNull(savedHistoryDTO);
        assertEquals(1, savedHistoryDTO.getJobPostingId());
        assertEquals(1, savedHistoryDTO.getUserId());
    }

    @Test
    void testDuplicateException() {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setJobPostingId(1);
        historyDTO.setUserId(1);

        when(historyRepository.existsByJobPostingIdAndUserId(1, 1)).thenReturn(true);

        assertThrows(DuplicateException.class, () -> historyService.applyToJob(historyDTO));
    }
}