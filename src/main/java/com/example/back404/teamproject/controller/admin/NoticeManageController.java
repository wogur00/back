package com.example.back404.teamproject.controller.admin;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NoticeManageController {

    // 공지사항 등록/수정/삭제 기능 (관리자 전용)
    @PostMapping(ApiMappingPattern.API_ADMIN + "/notices")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNotice(@RequestBody Object dto) {
        return ResponseEntity.ok("공지사항 등록");
    }

    @PutMapping(ApiMappingPattern.API_ADMIN + "/notices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateNotice(@PathVariable Long id, @RequestBody Object dto) {
        return ResponseEntity.ok("공지사항 수정");
    }

    @DeleteMapping(ApiMappingPattern.API_ADMIN + "/notices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteNotice(@PathVariable Long id) {
        return ResponseEntity.ok("공지사항 삭제");
    }
}