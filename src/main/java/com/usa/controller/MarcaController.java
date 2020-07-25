package com.usa.controller;

import com.usa.model.domain.modelo.Modelo;
import com.usa.model.dto.marca.ModelosByMarcaResponse;
import java.util.Optional;
import com.usa.model.dto.marca.MarcaByIdResponse;
import com.usa.util.Endpoint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.usa.model.domain.marca.Marca;
import java.util.List;
import com.usa.enums.Mensajes;
import com.usa.model.dto.marca.MarcaResponse;
import com.usa.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.usa.repository.MarcaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.marcas)
@CrossOrigin
public class MarcaController
{
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    
    @GetMapping
    public MarcaResponse getAllMarcas() {
        final List<Marca> marcas = marcaRepository.findAll();
        if (marcas.isEmpty()) {
            return MarcaResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return MarcaResponse.builder().respuesta(Mensajes.OK.get()).marcas(marcas).build();
    }
    
    @GetMapping("{marcaId}")
    public MarcaByIdResponse getMarcaById(@PathVariable final Long marcaId) {
        final Optional<Marca> marca = marcaRepository.findById(marcaId);
        if (!marca.isPresent()) {
            return MarcaByIdResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return MarcaByIdResponse.builder().respuesta(Mensajes.OK.get()).marca(marca.get()).build();
    }
    
    @GetMapping("{marcaId}/modelos")
    public ModelosByMarcaResponse getModelosByMarca(@PathVariable final Long marcaId) {
        final Optional<Marca> marca = marcaRepository.findById(marcaId);
        if (!marca.isPresent()) {
            return ModelosByMarcaResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        final List<Modelo> modelosByMarca = modeloRepository.findByMarca(marca.get());
        if (modelosByMarca.isEmpty()) {
            return ModelosByMarcaResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return ModelosByMarcaResponse.builder().respuesta(Mensajes.OK.get()).modelos(modelosByMarca).build();
    }
}
