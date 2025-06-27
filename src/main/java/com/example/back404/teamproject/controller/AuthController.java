package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.request.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.request.UserSignInRequestDto;
import com.example.back404.teamproject.service.SchoolAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final SchoolAuthService schoolAuthService;

    // AuthAdminController에서 관리자 인증 기능을 처리하므로
    // 여기서는 다른 관리자 관련 기능들을 처리합니다.

    @PutMapping("/api/v1/auth/admin/change")
    public ResponseEntity<ResponseDto<?>> changeAdmin(@RequestParam Long schoolId,
                                         @RequestBody @Valid SchoolSignUpRequestDto newAdminDto) {
        return ResponseEntity.ok(schoolAuthService.changeAdmin(schoolId, newAdminDto));
    }
    
    // 예시: 관리자 권한 관리, 시스템 설정 등
    @GetMapping("/api/v1/auth/admin/system-status")
    public ResponseEntity<?> getSystemStatus() {
        // TODO: 시스템 상태 확인 로직 구현 필요
        return ResponseEntity.ok(ResponseDto.setSuccess("시스템 상태 확인"));
    }
}