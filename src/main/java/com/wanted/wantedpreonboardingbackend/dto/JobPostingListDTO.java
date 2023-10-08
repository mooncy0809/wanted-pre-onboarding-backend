package com.wanted.wantedpreonboardingbackend.dto;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import lombok.Data;

import java.util.List;

@Data
public class JobPostingListDTO {
    private Integer id; //채용공고_id
    private String position; //채용포지션
    private Integer compensation; //채용보상금
    private String skill; //사용기술
    private String companyName; // 회사명
    private String country; // 국가
    private String location; // 지역

    public JobPostingListDTO(JobPosting jobPosting) {
        this.id = jobPosting.getId();
        this.position = jobPosting.getPosition();
        this.compensation = jobPosting.getCompensation();
        this.skill = jobPosting.getSkill();
        this.companyName = jobPosting.getCompany().getName();
        this.country = jobPosting.getCompany().getCountry();
        this.location = jobPosting.getCompany().getLocation();
    }

}
