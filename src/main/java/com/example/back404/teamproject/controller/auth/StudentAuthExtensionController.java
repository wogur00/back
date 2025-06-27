package com.example.back404.teamproject.controller.auth;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.dto.auth.request.StudentFindIdRequestDto;
import com.example.back404.teamproject.dto.auth.request.StudentResetPasswordRequestDto;
import com.example.back404.teamproject.dto.auth.response.FindIdResponseDto;
import com.example.back404.teamproject.dto.auth.response.ResetPasswordResponseDto;
import com.example.back404.teamproject.dto.student.request.StudentSignUpRequestDto;
import com.example.back404.teamproject.service.StudentAuthExtensionService;
import com.example.back404.teamproject.service.StudentAuthService;
import com.example.back404.teamproject.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentAuthExtensionController {

    private final StudentAuthExtensionService studentAuthExtensionService;
    private final StudentAuthService studentAuthService;
    private final StudentService studentService;

    // ========== 학생 인증 관련 엔드포인트 ========== //
    @PostMapping(ApiMappingPattern.API_AUTH_STUDENT + "/signup")
    public ResponseEntity<ResponseDto<?>> studentSignup(@Valid @RequestBody StudentSignUpRequestDto requestDto) {
        try {
            studentService.signUp(requestDto);
            return ResponseEntity.ok(ResponseDto.setSuccess("학생 회원가입이 완료되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.setFailed(e.getMessage()));
        }
    }

    @PostMapping(ApiMappingPattern.API_AUTH_STUDENT + "/login")
    public ResponseEntity<ResponseDto<?>> studentLogin(@Valid @RequestBody LoginRequestDto requestDto) {
        try {
            String token = studentAuthService.login(requestDto);
            return ResponseEntity.ok(ResponseDto.setSuccess("로그인 성공", token));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.setFailed(e.getMessage()));
        }
    }

    @PostMapping(ApiMappingPattern.API_AUTH_STUDENT + "/find-id")
    public ResponseEntity<ResponseDto<FindIdResponseDto>> studentFindId(@Valid @RequestBody StudentFindIdRequestDto requestDto) {
        ResponseDto<FindIdResponseDto> response = studentAuthExtensionService.findStudentId(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiMappingPattern.API_AUTH_STUDENT + "/find-password")
    public ResponseEntity<ResponseDto<ResetPasswordResponseDto>> studentFindPassword(@Valid @RequestBody StudentResetPasswordRequestDto requestDto) {
        ResponseDto<ResetPasswordResponseDto> response = studentAuthExtensionService.resetStudentPassword(requestDto);
        return ResponseEntity.ok(response);
    }
}