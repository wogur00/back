package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.dto.school.request.SchoolUpdateRequestDto;
import com.example.back404.teamproject.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 학교 관리 컨트롤러
 * - 공통: 학교 목록 조회 (인증된 사용자)
 * - 관리자: 개별 학교 정보 조회/수정
 */
@RestController
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    // ========== 공통 기능 (인증된 사용자) ========== //
    
    /**
     * 전체 학교 목록 조회 (인증된 사용자)
     * GET /api/v1/schools
     */
    @GetMapping(ApiMappingPattern.SCHOOLS_BASE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    // ========== 관리자 전용 기능 ========== //
    
    /**
     * 개별 학교 정보 조회 (관리자)
     * GET /api/v1/school/{id}
     */
    @GetMapping("/api/v1/school/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getSchoolInfo(@PathVariable Long id) {
        return ResponseEntity.ok(schoolService.getSchoolInfo(id));
    }

    /**
     * 학교 정보 수정 (관리자)
     * PUT /api/v1/school/{id}
     */
    @PutMapping("/api/v1/school/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSchoolInfo(@PathVariable Long id,
                                              @RequestBody @Valid SchoolUpdateRequestDto dto) {
        return ResponseEntity.ok(schoolService.updateSchoolInfo(id, dto));
    }
}