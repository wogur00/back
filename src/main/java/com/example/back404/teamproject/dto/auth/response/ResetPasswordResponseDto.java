package com.example.back404.teamproject.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResetPasswordResponseDto {
    private String message;
    private String temporaryPassword;
}