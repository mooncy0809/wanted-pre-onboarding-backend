package com.wanted.wantedpreonboardingbackend.dto;

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
    private String companyName; // 회사명
    private String country; // 국가
    private String location; // 지역
    private List<Integer> otherJobPostingids; // 회사가 올린 다른 채용공고
}
