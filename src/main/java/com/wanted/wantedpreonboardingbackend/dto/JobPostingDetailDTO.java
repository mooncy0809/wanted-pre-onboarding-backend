package com.wanted.wantedpreonboardingbackend.dto;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import lombok.Data;

import java.util.List;

@Data
public class JobPostingDetailDTO {
    private Integer id; //채용공고_id
    private String position; //채용포지션
    private String detail; //채용내용
    private Integer compensation; //채용보상금
    private String skill; //사용기술
    private String companyName; // 회사명
    private String country; // 국가
    private String location; // 지역
    private List<Integer> otherJobPostingids; // 회사가 올린 다른 채용공고


    public JobPostingDetailDTO(JobPosting jobPosting, List<Integer> otherJobPostingids) {
        this.id = jobPosting.getId();
        this.position = jobPosting.getPosition();
        this.compensation = jobPosting.getCompensation();
        this.skill = jobPosting.getSkill();
        this.detail = jobPosting.getDetail();
        this.companyName = jobPosting.getCompany().getName();
        this.country = jobPosting.getCompany().getCountry();
        this.location = jobPosting.getCompany().getLocation();
        this.otherJobPostingids = otherJobPostingids;
    }
}
