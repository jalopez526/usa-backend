// 
// Decompiled by Procyon v0.5.36
// 

package com.usa.controller;

import com.usa.util.Endpoint;
import org.springframework.web.bind.annotation.GetMapping;
import com.usa.model.domain.tipo.Tipo;
import java.util.List;
import com.usa.enums.Mensajes;
import com.usa.model.dto.tipo.GetAllTipoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.usa.repository.TipoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.tipos)
@CrossOrigin
public class TipoController
{
    @Autowired
    private TipoRepository tipoRepository;
    
    @GetMapping
    public GetAllTipoResponse getAllTipo() {
        final List<Tipo> tipos = (List<Tipo>)tipoRepository.findAll();
        if (tipos.isEmpty()) {
            return GetAllTipoResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return GetAllTipoResponse.builder().tipos((List)tipos).respuesta(Mensajes.OK.get()).build();
    }
}
