package com.usa.model.dto.auth;

import lombok.Data;

@Data
public class OAuthTokenErrorResponse {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
