package com.usa.service.impl;

import com.usa.model.dto.auth.AuthRequest;
import com.usa.model.dto.auth.OAuthTokenSuccessResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class AuthService {
    @Value("${service.auth.url}")
    private String authUrl;
    @Value("${service.auth.client}")
    private String authClient;
    @Value("${service.auth.secret}")
    private String authSecret;
    @Autowired
    private RestTemplate restTemplate;
    
    public OAuthTokenSuccessResponse signIn(final AuthRequest request) {
        final HttpEntity<AuthRequest> entity = new HttpEntity<>(request, createHeaders(authClient, authSecret));
        return this.restTemplate.exchange(authUrl, HttpMethod.POST, entity, OAuthTokenSuccessResponse.class, request.getUser(), request.getPassword()).getBody();
    }

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}
