package com.example.back404.teamproject.controller.lecture;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 강의 관리 컨트롤러
 * - 교사: 강의 생성, 수정, 관리
 * - 학생: 강의 조회
 */
@RestController
@RequiredArgsConstructor
public class LectureController {

    // ========== 교사 전용 강의 관리 기능 ========== //
    
    /**
     * 교사 본인 강의 목록 조회 (교사만)
     * GET /api/v1/lectures/teacher
     */
    @GetMapping(ApiMappingPattern.LECTURES_BASE + "/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> getMyLectures() {
        return ResponseEntity.ok("내 강의 목록");
    }

    /**
     * 강의 생성 (교사만)
     * POST /api/v1/lectures
     */
    @PostMapping(ApiMappingPattern.LECTURES_BASE)
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> createLecture(@RequestBody Object dto) {
        return ResponseEntity.ok("강의 등록");
    }

    /**
     * 강의 수정 (교사만)
     * PUT /api/v1/lectures/{lectureId}
     */
    @PutMapping(ApiMappingPattern.LECTURES_BASE + "/{lectureId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> updateLecture(@PathVariable Long lectureId, @RequestBody Object dto) {
        return ResponseEntity.ok("강의 수정");
    }

    // ========== 학생 전용 강의 조회 기능 ========== //
    
    /**
     * 전체 강의 목록 조회 (학생만)
     * GET /api/v1/lectures
     */
    @GetMapping(ApiMappingPattern.LECTURES_BASE)
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getAllLectures() {
        return ResponseEntity.ok("강의 목록 조회");
    }

    /**
     * 강의 상세 조회 (학생만)
     * GET /api/v1/lectures/{lectureId}
     */
    @GetMapping(ApiMappingPattern.LECTURES_BASE + "/{lectureId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getLectureDetail(@PathVariable Long lectureId) {
        return ResponseEntity.ok("강의 상세 조회");
    }
}