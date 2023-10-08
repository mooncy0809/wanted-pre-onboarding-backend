package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.History;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.domain.User;
import com.wanted.wantedpreonboardingbackend.dto.HistoryDTO;
import com.wanted.wantedpreonboardingbackend.exception.DuplicateException;
import com.wanted.wantedpreonboardingbackend.repository.HistoryRepository;
import com.wanted.wantedpreonboardingbackend.repository.JobPostingRepository;
import com.wanted.wantedpreonboardingbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private UserRepository userRepository;

    public HistoryDTO applyToJob(HistoryDTO historyDTO) {
        Integer jobPostingId = historyDTO.getJobPostingId();
        Integer userId = historyDTO.getUserId();

        if (historyRepository.existsByJobPostingIdAndUserId(jobPostingId, userId)) {
            throw new DuplicateException("사용자는 1회만 지원 가능합니다.");
        }

        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("해당 id의 채용공고 찾을 수 없음 : " + jobPostingId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 id의 사용자 찾을 수 없음: " + userId));

        History history = new History();
        history.setJobPosting(jobPosting);
        history.setUser(user);

        History savedHistory = historyRepository.save(history);

        historyDTO.setId(savedHistory.getId());
        historyDTO.setJobPostingId(savedHistory.getJobPosting().getId());
        historyDTO.setUserId(savedHistory.getUser().getId());

        return historyDTO;
    }
}
