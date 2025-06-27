package com.example.back404.teamproject.controller.subject;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.subject.request.SubjectStatusUpdateRequestDto;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 과목 관리자 전용 컨트롤러
 * - 관리자: 과목 승인/거부, 상태 관리
 * - 관리자: 승인 대기 과목 조회
 */
@RestController
@RequestMapping(ApiMappingPattern.API_MANAGE_SUBJECT)
@RequiredArgsConstructor
public class SubjectManageController {

    private final SubjectService subjectService;

    // ========== 관리자 전용 과목 관리 기능 ========== //
    
    /**
     * 승인 대기 과목 목록 조회 (관리자만)
     * GET /api/v1/manage/subjects
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<List<SubjectListGetResponseDto>>> getPendingSubjects(
            @AuthenticationPrincipal String username,
            @RequestParam(required = false) String subjectName,
            @RequestParam(required = false) String subjectGrade,
            @RequestParam(required = false) String subjectSemester,
            @RequestParam(required = false) SubjectAffiliation subjectAffiliation
    ) {
        ResponseDto<List<SubjectListGetResponseDto>> results = subjectService.searchSubjects(
                username, subjectName, subjectGrade, subjectSemester, subjectAffiliation);
        return ResponseEntity.ok(results);
    }

    /**
     * 승인 대기 과목 상세 조회 (관리자만)
     * GET /api/v1/manage/subjects/{subjectId}
     */
    @GetMapping("/{subjectId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<SubjectGetResponseDto>> getPendingSubjectById(
            @AuthenticationPrincipal String username,
            @PathVariable String subjectId
    ) {
        ResponseDto<SubjectGetResponseDto> result = subjectService.getSubjectById(username, subjectId);
        return ResponseEntity.ok(result);
    }

    /**
     * 과목 승인/거부 상태 변경 (관리자만)
     * PUT /api/v1/manage/subjects/{subjectId}/status
     */
    @PutMapping("/{subjectId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<SubjectGetResponseDto>> updateSubjectStatus(
            @AuthenticationPrincipal String username,
            @PathVariable String subjectId,
            @Valid @RequestBody SubjectStatusUpdateRequestDto requestDto
    ) {
        ResponseDto<SubjectGetResponseDto> response = subjectService.updateSubjectStatus(
                username, subjectId, requestDto.getStatus());
        return ResponseEntity.ok(response);
    }
}