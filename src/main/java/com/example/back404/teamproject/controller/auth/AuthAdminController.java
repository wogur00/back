package com.example.back404.teamproject.controller.auth;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.request.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.request.UserSignInRequestDto;
import com.example.back404.teamproject.service.SchoolAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthAdminController {

    private final SchoolAuthService schoolAuthService;

    @PostMapping(ApiMappingPattern.API_AUTH_ADMIN + "/signup")
    public ResponseEntity<?> signup(@RequestBody SchoolSignUpRequestDto dto) {
        return ResponseEntity.ok(schoolAuthService.signUp(dto));
    }

    @PostMapping(ApiMappingPattern.API_AUTH_ADMIN + "/login")
    public ResponseEntity<?> login(@RequestBody UserSignInRequestDto dto) {
        return ResponseEntity.ok(schoolAuthService.signIn(dto));
    }

    @PostMapping(ApiMappingPattern.API_AUTH_ADMIN + "/find-password")
    public ResponseEntity<?> findPassword(@RequestBody Object dto) {
        // TODO: 관리자 비밀번호 찾기 로직 구현 필요
        return ResponseEntity.ok(ResponseDto.setSuccess("관리자 비밀번호 찾기"));
    }
}
