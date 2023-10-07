package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingDTO;
import com.wanted.wantedpreonboardingbackend.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobposting")
public class JobPostingController {
    @Autowired
    private JobPostingService jobPostingService;

    @PostMapping("/create")
    public ResponseEntity<JobPosting> createJobPosting(@RequestBody JobPostingDTO jobPostingDTO) {
        JobPosting jobPosting = jobPostingService.createJobPosting(jobPostingDTO);
        return new ResponseEntity<>(jobPosting, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobPosting> updateJobPosting(@PathVariable Integer id, @RequestBody JobPostingDTO jobPostingDTO) {
        JobPosting jobPosting = jobPostingService.updateJobPosting(id, jobPostingDTO);
        return new ResponseEntity<>(jobPosting, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJobPosting(@PathVariable Integer id) {
        jobPostingService.deleteJobPosting(id);
        return new ResponseEntity<>("채용공고가 삭제되었음.", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<JobPostingDTO>> getJobPostingsList() {
        List<JobPostingDTO> jobpostings = jobPostingService.getJobPostingsList();
        return ResponseEntity.ok(jobpostings);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobPostingDTO>> searchJobPosting(@RequestParam("search") String keyword) {
        List<JobPostingDTO> jobpostings = jobPostingService.searchJobPosting(keyword);
        return ResponseEntity.ok(jobpostings);
    }
}
