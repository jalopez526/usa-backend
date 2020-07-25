package com.usa.controller;

import com.usa.enums.Mensajes;
import com.usa.model.dto.auth.AuthRequest;
import com.usa.model.dto.auth.AuthResponse;
import com.usa.model.dto.auth.OAuthTokenSuccessResponse;
import com.usa.service.impl.AuthService;
import com.usa.util.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.auth)
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping
    public AuthResponse auth(@RequestBody final AuthRequest request) {
        try {
            final OAuthTokenSuccessResponse tokenResponse = this.authService.signIn(request);
            return AuthResponse.builder().tokenResponse(tokenResponse).respuesta(Mensajes.OK.get()).build();
        }
        catch (Exception e) {
            if (e.getMessage().toLowerCase().contains("bad credentials")) {
                return AuthResponse.builder().respuesta(Mensajes.USUARIO_O_CONTRASENA_INCORRECTA.get()).build();
            }
            return AuthResponse.builder().respuesta(Mensajes.ERROR_INESPERADO.get()).build();
        }
    }
}
