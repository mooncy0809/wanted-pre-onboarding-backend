package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingDTO;
import com.wanted.wantedpreonboardingbackend.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
