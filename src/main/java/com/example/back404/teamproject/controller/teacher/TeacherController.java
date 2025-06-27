package com.example.back404.teamproject.controller.teacher;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.teacher.response.TeacherListGetResponseDto;
import com.example.back404.teamproject.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 교사 관리 컨트롤러
 * - 교사: 본인 정보 관리, 대시보드
 * - 관리자: 교사 목록 조회, 교사 관리
 */
@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    // ========== 교사 본인 기능 ========== //
    
    /**
     * 교사 대시보드 (교사만)
     * GET /api/v1/teacher/dashboard
     */
    @GetMapping(ApiMappingPattern.API_TEACHER + "/dashboard")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> getDashboard() {
        // TODO: 교사 대시보드 로직 구현 필요
        return ResponseEntity.ok(ResponseDto.setSuccess("교사 대시보드"));
    }

    // ========== 관리자용 교사 관리 기능 ========== //
    
    /**
     * 전체 교사 목록 조회 (관리자, 교사)
     * GET /api/v1/manage/teachers
     */
    @GetMapping(ApiMappingPattern.API_MANAGE_TEACHER)
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<TeacherListGetResponseDto>>> getTeacherList(
            @AuthenticationPrincipal String username
    ) {
        ResponseDto<List<TeacherListGetResponseDto>> response = teacherService.getTeacherList(username);
        return ResponseEntity.ok(response);
    }
}
