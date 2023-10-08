package com.wanted.wantedpreonboardingbackend.dto;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import lombok.Data;

import java.util.List;

@Data
public class JobPostingDTO {
    private Integer id; //채용공고_id
    private Integer companyId; //회사_id
    private String position; //채용포지션
    private String detail; //채용내용
    private Integer compensation; //채용보상금
    private String skill; //사용기술
  }
