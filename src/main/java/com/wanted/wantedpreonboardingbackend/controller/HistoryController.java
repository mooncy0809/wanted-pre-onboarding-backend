package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.dto.HistoryDTO;
import com.wanted.wantedpreonboardingbackend.exception.DuplicateException;
import com.wanted.wantedpreonboardingbackend.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/jobApply")
    public ResponseEntity<?> applyToJob(@RequestBody HistoryDTO historyDTO) {
        try {
            HistoryDTO savedHistoryDTO = historyService.applyToJob(historyDTO);
            return ResponseEntity.ok(savedHistoryDTO);
        } catch (DuplicateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "사용자는 1회만 지원 가능합니다."));
        }
    }
}
