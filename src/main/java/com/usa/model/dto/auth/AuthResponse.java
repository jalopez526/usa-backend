package com.usa.model.dto.auth;

import com.usa.model.dto.respuesta.Respuesta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class AuthResponse {
    private Respuesta respuesta;
    private OAuthTokenSuccessResponse tokenResponse;
}
