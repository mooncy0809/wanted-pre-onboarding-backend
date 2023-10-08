package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.History;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.domain.User;
import com.wanted.wantedpreonboardingbackend.dto.HistoryDTO;
import com.wanted.wantedpreonboardingbackend.repository.HistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class HistoryServiceTest {
    @Autowired
    private HistoryService historyService;

    @Mock
    private HistoryRepository historyRepository;

    @DisplayName("applyToJob: 채용 지원 성공.")
    @Test
    void testApplyToJob() {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setJobPostingId(1);
        historyDTO.setUserId(1);

        when(historyRepository.save(any(History.class))).thenAnswer(invocation -> {
            Object savedHistory = invocation.getArgument(0);
            return savedHistory;
        });

        HistoryDTO savedHistoryDTO = historyService.applyToJob(historyDTO);

        assertNotNull(savedHistoryDTO);
    }
}
