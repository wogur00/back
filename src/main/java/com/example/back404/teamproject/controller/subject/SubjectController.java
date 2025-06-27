package com.example.back404.teamproject.controller.subject;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.dto.subject.request.SubjectCreateRequestDto;
import com.example.back404.teamproject.dto.subject.request.SubjectUpdateRequestDto;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.service.SubjectService;
import com.example.back404.teamproject.service.SubjectManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 과목 관리 컨트롤러
 * - 교사: 과목 생성, 수정, 삭제, 조회
 * - 관리자/교사: 과목 조회
 */
@RestController
@RequestMapping(ApiMappingPattern.SUBJECTS_BASE)
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectManagementService subjectManagementService;
    private final JwtProvider jwtProvider;

    // ========== 조회 기능 (관리자, 교사 공통) ========== //
    
    /**
     * 과목 목록 조회 (관리자, 교사)
     * GET /api/v1/subjects
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<SubjectListGetResponseDto>>> searchSubjects(
            @AuthenticationPrincipal String email,
            @RequestParam(required = false) String subjectName,
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String semester,
            @RequestParam(required = false) SubjectAffiliation subjectAffiliation
    ) {
        ResponseDto<List<SubjectListGetResponseDto>> results = subjectService.searchSubjects(
                email, subjectName, grade, semester, subjectAffiliation);
        return ResponseEntity.ok(results);
    }

    /**
     * 과목 상세 조회 (관리자, 교사)
     * GET /api/v1/subjects/{subjectId}
     */
    @GetMapping("/{subjectId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<SubjectGetResponseDto>> getSubjectById(
            @AuthenticationPrincipal String userId,
            @PathVariable String subjectId
    ) {
        ResponseDto<SubjectGetResponseDto> result = subjectService.getSubjectById(userId, subjectId);
        return ResponseEntity.ok(result);
    }

    // ========== 교사 전용 기능 ========== //
    
    /**
     * 과목 생성 (교사만)
     * POST /api/v1/subjects
     */
    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ResponseDto<?>> createSubject(
            @Valid @RequestBody SubjectCreateRequestDto requestDto,
            HttpServletRequest request) {
        
        String teacherEmail = jwtProvider.getEmailFromRequest(request);
        ResponseDto<?> response = subjectManagementService.createSubject(requestDto, teacherEmail);
        return ResponseEntity.ok(response);
    }

    /**
     * 과목 수정 (교사만)
     * PUT /api/v1/subjects/{subjectId}
     */
    @PutMapping("/{subjectId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ResponseDto<?>> updateSubject(
            @PathVariable String subjectId,
            @Valid @RequestBody SubjectUpdateRequestDto requestDto,
            HttpServletRequest request) {
        
        String teacherEmail = jwtProvider.getEmailFromRequest(request);
        ResponseDto<?> response = subjectManagementService.updateSubject(subjectId, requestDto, teacherEmail);
        return ResponseEntity.ok(response);
    }

    /**
     * 과목 삭제 (교사만)
     * DELETE /api/v1/subjects/{subjectId}
     */
    @DeleteMapping("/{subjectId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ResponseDto<?>> deleteSubject(
            @PathVariable String subjectId,
            HttpServletRequest request) {
        
        String teacherEmail = jwtProvider.getEmailFromRequest(request);
        ResponseDto<?> response = subjectManagementService.deleteSubject(subjectId, teacherEmail);
        return ResponseEntity.ok(response);
    }
}