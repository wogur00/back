package com.example.back404.teamproject.controller.notice;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    // 공지사항 열람 (공통 - 모든 인증된 사용자)
    @GetMapping(ApiMappingPattern.NOTICES_BASE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllNotices(@RequestParam(required = false) String category) {
        return ResponseEntity.ok("공지사항 목록 조회");
    }

    @GetMapping(ApiMappingPattern.NOTICES_BASE + "/{noticeId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getNoticeDetail(@PathVariable Long noticeId) {
        return ResponseEntity.ok("공지사항 상세 조회");
    }
}