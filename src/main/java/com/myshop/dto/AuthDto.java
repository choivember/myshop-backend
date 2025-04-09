package com.myshop.dto;

import lombok.Data;

public class AuthDto {
    @Data
    public static class SignupRequest {
        private String username;
        private String email;
        private String password;
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }
}