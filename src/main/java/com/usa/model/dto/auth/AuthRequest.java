package com.usa.model.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String user;
    private String password;
}
