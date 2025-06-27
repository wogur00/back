package com.example.back404.teamproject.controller.student;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.student.request.StudentUpdateRequestDto;
import com.example.back404.teamproject.dto.student.response.StudentInfoResponseDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 학생 관리 컨트롤러
 * - 학생: 본인 정보 조회/수정, 대시보드
 * - 관리자/교사: 학생 목록 조회, 학생 정보 관리
 */
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // ========== 학생 본인 기능 ========== //
    
    /**
     * 학생 본인 정보 조회 (학생만)
     * GET /api/v1/student/{studentId}
     */
    @GetMapping(ApiMappingPattern.API_STUDENT + "/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentInfoResponseDto> getStudentInfo(@PathVariable Long studentId) {
        StudentInfoResponseDto info = studentService.getStudentInfo(studentId);
        return ResponseEntity.ok(info);
    }

    /**
     * 학생 본인 정보 수정 (학생만)
     * PUT /api/v1/student/{studentId}
     */
    @PutMapping(ApiMappingPattern.API_STUDENT + "/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> updateStudentInfo(
            @PathVariable Long studentId,
            @Valid @RequestBody StudentUpdateRequestDto request) {
        studentService.updateStudentInfo(studentId, request);
        return ResponseEntity.ok("학생 정보가 수정되었습니다.");
    }
    
    /**
     * 학생 대시보드 (학생만)
     * GET /api/v1/student/dashboard
     */
    @GetMapping(ApiMappingPattern.API_STUDENT + "/dashboard")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getDashboard() {
        // TODO: 학생 대시보드 로직 구현 필요
        return ResponseEntity.ok(ResponseDto.setSuccess("학생 대시보드"));
    }

    // ========== 관리자/교사용 학생 관리 기능 ========== //
    
    /**
     * 전체 학생 목록 조회 (관리자, 교사)
     * GET /api/v1/manage/students
     */
    @GetMapping(ApiMappingPattern.API_MANAGE_STUDENT)
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok("학생 목록 조회");
    }

    /**
     * 학생 상세 정보 조회 (관리자, 교사)
     * GET /api/v1/manage/students/{studentId}
     */
    @GetMapping(ApiMappingPattern.API_MANAGE_STUDENT + "/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getStudentDetail(@PathVariable String studentId) {
        return ResponseEntity.ok("학생 상세 조회");
    }
}