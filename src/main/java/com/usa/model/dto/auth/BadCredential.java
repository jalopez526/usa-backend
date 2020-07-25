package com.usa.model.dto.auth;

import lombok.Data;

@Data
public class BadCredential {
    private String error;
    private String error_description;
}
