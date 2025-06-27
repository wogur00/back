package com.example.back404.teamproject.controller.registration;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 수강신청 관리 컨트롤러
 * - 학생: 수강신청, 수강신청 취소, 본인 수강신청 목록 조회
 */
@RestController
@RequiredArgsConstructor
public class CourseRegistrationController {

    // ========== 학생 전용 수강신청 기능 ========== //
    
    /**
     * 수강신청 (학생만)
     * POST /api/v1/course-registrations
     */
    @PostMapping(ApiMappingPattern.STUDENT_REGISTRATIONS)
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> applyCourse(@RequestBody Object dto) {
        return ResponseEntity.ok("수강 신청");
    }

    /**
     * 본인 수강신청 목록 조회 (학생만)
     * GET /api/v1/course-registrations
     */
    @GetMapping(ApiMappingPattern.STUDENT_REGISTRATIONS)
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getMyCourseApplications() {
        return ResponseEntity.ok("내 수강신청 목록");
    }

    /**
     * 수강신청 취소 (학생만)
     * DELETE /api/v1/course-registrations/{registrationId}
     */
    @DeleteMapping(ApiMappingPattern.STUDENT_REGISTRATIONS + "/{registrationId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> cancelCourseApplication(@PathVariable Long registrationId) {
        return ResponseEntity.ok("수강신청 취소");
    }
}