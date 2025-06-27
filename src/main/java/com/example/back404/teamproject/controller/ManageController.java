package com.example.back404.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 관리자 전용 관리 컨트롤러
 * - 관리자: 교사/학생 상태 관리, 삭제
 * - 관리자: 시스템 전체 관리 기능
 */
@RestController
@RequestMapping("/api/v1/manage")
@RequiredArgsConstructor
public class ManageController {

    // ========== 관리자 전용 관리 기능 ========== //
    
    /**
     * 교사 상태 변경 (관리자만)
     * PUT /api/v1/manage/teacher/status
     */
    @PutMapping("/teacher/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTeacherStatus(@RequestBody Object dto) {
        return ResponseEntity.ok("교사 상태 변경");
    }

    /**
     * 학생 상태 변경 (관리자만)
     * PUT /api/v1/manage/student/status
     */
    @PutMapping("/student/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStudentStatus(@RequestBody Object dto) {
        return ResponseEntity.ok("학생 상태 변경");
    }

    /**
     * 교사 삭제 (관리자만)
     * DELETE /api/v1/manage/teacher/{id}
     */
    @DeleteMapping("/teacher/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        return ResponseEntity.ok("교사 삭제");
    }

    /**
     * 학생 삭제 (관리자만)
     * DELETE /api/v1/manage/student/{id}
     */
    @DeleteMapping("/student/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok("학생 삭제");
    }
}
