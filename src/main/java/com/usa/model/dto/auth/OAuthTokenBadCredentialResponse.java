package com.usa.model.dto.auth;

import lombok.Data;

import java.util.List;

@Data
public class OAuthTokenBadCredentialResponse {
    private List<BadCredential> badCredentials;
}
