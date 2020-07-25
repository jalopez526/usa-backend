
package com.usa.controller;

import com.usa.enums.EstadoArticulo;
import com.usa.enums.Mensajes;
import com.usa.model.domain.articulo.Articulo;
import com.usa.model.domain.articulo.ArticuloMarcaModelo;
import com.usa.model.domain.marca.Marca;
import com.usa.model.domain.modelo.Modelo;
import com.usa.model.domain.tipo.Tipo;
import com.usa.model.dto.articulo.*;
import com.usa.model.dto.respuesta.Respuesta;
import com.usa.repository.*;
import com.usa.util.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(Endpoint.articulos)
@CrossOrigin
public class ArticuloController
{
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private ArticuloMarcaModeloRepository articuloMarcaModeloRepository;
    @Autowired
    private TipoRepository tipoRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ALL', 'CREATE_ARTICULOS')")
    public SaveArticuloResponse saveArticulo(@RequestBody final SaveArticuloRequest request) {
        final Optional<Tipo> tipoOptional = tipoRepository.findById(Long.valueOf(request.getArticulo().getTipo()));
        if (!tipoOptional.isPresent()) {
            return SaveArticuloResponse.builder().respuesta(Mensajes.DATOS_INCORRECTOS.get()).build();
        }

        final List<ArticuloResponse> response = new ArrayList<>();
        for (int i = 1; i <= request.getCantidad(); ++i) {
            final List<ArticuloMarcaModelo> articuloMarcaModelos = new ArrayList<>();
            final Articulo articulo = Articulo.builder()
                    .nombre(request.getArticulo().getNombre())
                    .tipo(tipoOptional.get())
                    .codigo(request.getArticulo().getCodigo())
                    .ubicacion(request.getArticulo().getUbicacion())
                    .precioPorMayor(new BigDecimal(request.getArticulo().getPrecioPorMayor()))
                    .precioDetalle(new BigDecimal(request.getArticulo().getPrecioDetalle()))
                    .precioAlterno(new BigDecimal(request.getArticulo().getPrecioPorMayor()))
                    .esNuevo(request.getArticulo().isEsNuevo())
                    .fechaCreacion(new Date())
                    .estado(EstadoArticulo.CREADO.get())
                    .build();
            final Articulo newArticulo = articuloRepository.save(articulo);
            
            for (final MarcaModelo marcaModelo : request.getArticulo().getMarcaModelo()) {
                final Optional<Marca> marca = marcaRepository.findById(Long.valueOf(marcaModelo.getMarca()));
                final List<Modelo> modelos = modeloRepository.findByMarca(marca.get());
                final Optional<Modelo> modelo = modelos.stream().filter(m -> m.getId().equals(Long.valueOf(marcaModelo.getModelo()))).findAny();
                articuloMarcaModelos.add(ArticuloMarcaModelo.builder()
                        .anio(marcaModelo.getAnio())
                        .marca(marca.get())
                        .modelo(modelo.get())
                        .build());
            }
            articuloMarcaModelos.forEach(a -> a.setArticulo(newArticulo));
            final List<ArticuloMarcaModelo> newArticuloMarcaModelos = articuloMarcaModeloRepository.saveAll(articuloMarcaModelos);
            response.add(ArticuloResponse.builder().articulo(newArticulo).articuloMarcasModelos(newArticuloMarcaModelos).build());
        }
        
        return SaveArticuloResponse.builder().respuesta(Mensajes.OK.get()).response(response).build();
    }
    
    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ALL', 'UPDATE_ARTICULOS')")
    public UpdateArticuloResponse updateArticulo(@PathVariable final Long id, @RequestBody final UpdateArticuloRequest request) {
        final Optional<Articulo> articuloOptional = articuloRepository.findById(id);
        if (!articuloOptional.isPresent()) {
            return UpdateArticuloResponse.builder().respuesta(Mensajes.DATOS_INCORRECTOS.get()).build();
        }
        final Optional<Tipo> tipo = tipoRepository.findById(Long.valueOf(request.getTipo()));
        if (!tipo.isPresent()) {
            return UpdateArticuloResponse.builder().respuesta(Mensajes.DATOS_INCORRECTOS.get()).build();
        }
        final List<ArticuloMarcaModelo> articuloMarcaModelos = new ArrayList<>();
        for (final UpdateMarcaModelo marcaModelo : request.getMarcaModelo()) {
            if (Objects.isNull(marcaModelo)) {
                continue;
            }
            final Optional<Marca> marca = marcaRepository.findById(Long.valueOf(marcaModelo.getMarca()));
            if (!marca.isPresent()) {
                return UpdateArticuloResponse.builder().respuesta(Mensajes.DATOS_INCORRECTOS.get()).build();
            }
            final List<Modelo> modelos = modeloRepository.findByMarca(marca.get());
            final Optional<Modelo> modelo = modelos.stream().filter(m -> m.getId().equals(Long.valueOf(marcaModelo.getModelo()))).findAny();
            if (!modelo.isPresent()) {
                return UpdateArticuloResponse.builder().respuesta(Mensajes.DATOS_INCORRECTOS.get()).build();
            }
            articuloMarcaModelos.add(ArticuloMarcaModelo.builder().id(marcaModelo.getId()).anio(marcaModelo.getAnio()).marca(marca.get()).modelo(modelo.get()).build());
        }
        final Articulo articulo = Articulo.builder().id(id).nombre(request.getNombre()).tipo((Tipo)tipo.get()).codigo(request.getCodigo()).ubicacion(request.getUbicacion()).precioPorMayor(new BigDecimal(request.getPrecioPorMayor())).precioDetalle(new BigDecimal(request.getPrecioDetalle())).precioAlterno(new BigDecimal(request.getPrecioPorMayor())).esNuevo(request.isEsNuevo()).fechaCreacion(new Date()).estado(EstadoArticulo.CREADO.get()).build();
        final Articulo updatedArticulo = articuloRepository.save(articulo);
        articuloMarcaModeloRepository.deleteByArticulo(updatedArticulo);
        for (final ArticuloMarcaModelo a : articuloMarcaModelos) {
            a.setId(null);
            a.setArticulo(updatedArticulo);
        }
        final List<ArticuloMarcaModelo> updatedArticuloMarcaModelos = articuloMarcaModeloRepository.saveAll(articuloMarcaModelos);
        return UpdateArticuloResponse.builder().respuesta(Mensajes.OK.get()).articulo(updatedArticulo).articuloMarcasModelos(updatedArticuloMarcaModelos).build();
    }
    
    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ALL', 'READ_ARTICULOS')")
    public GetArticuloByIdResponse getArticuloById(@PathVariable final Long id) {
        final Optional<Articulo> articulo = articuloRepository.findById(id);
        if (!articulo.isPresent()) {
            return GetArticuloByIdResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return GetArticuloByIdResponse.builder().articulo(articulo.get()).respuesta(Mensajes.OK.get()).build();
    }
    
    @GetMapping("{id}/marcaModelos")
    @PreAuthorize("hasAnyAuthority('ALL', 'READ_ARTICULOS')")
    public MarcaModeloResponse getMarcasModelosByArticulo(@PathVariable final Long id) {
        final Optional<Articulo> articulo = articuloRepository.findById(id);
        if (!articulo.isPresent()) {
            return MarcaModeloResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        final List<ArticuloMarcaModelo> articuloMarcaModelos = articuloMarcaModeloRepository.findByArticulo(articulo.get());
        if (articuloMarcaModelos.isEmpty()) {
            return MarcaModeloResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return MarcaModeloResponse.builder().articuloMarcaModelos(articuloMarcaModelos).respuesta(Mensajes.OK.get()).build();
    }
    
    @PostMapping("{id}/despachar")
    @PreAuthorize("hasAnyAuthority('ALL', 'DESPACHANDO')")
    public GetArticuloByIdResponse setArticuloToDespachando(@PathVariable final Long id) {
        final Optional<Articulo> articulo = articuloRepository.findById(id);
        if (!articulo.isPresent()) {
            return GetArticuloByIdResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        articulo.get().setEstado(EstadoArticulo.DESPACHANDO.get());
        articuloRepository.save(articulo.get());
        return GetArticuloByIdResponse.builder().articulo(articulo.get()).respuesta(Mensajes.OK.get()).build();
    }
    
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ALL', 'READ_ARTICULOS')")
    public GetArticulosResponse getArticulos(@RequestParam(required = false) final String nombre, @RequestParam(required = false) final Long tipo, @RequestParam(required = false) final Long marca, @RequestParam(required = false) final Long modelo, @RequestParam(required = false) final String anio) {
        final List<Articulo> articulos = articuloRepository.findArticulos(nombre, tipo, marca, modelo, anio);
        if (articulos.isEmpty()) {
            return GetArticulosResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return GetArticulosResponse.builder().articulos(articulos).respuesta(Mensajes.OK.get()).build();
    }
    
    @GetMapping("/despachando")
    @PreAuthorize("hasAnyAuthority('ALL', 'DESPACHANDO')")
    public ArticuloDespachoResponse getDespachos() {
        final List<Articulo> articuloByEstado = articuloRepository.findArticuloByEstado(EstadoArticulo.DESPACHANDO.get());
        final List<ArticuloDespacho> articuloDespachos = new ArrayList<>();
        for (final Articulo articulo : articuloByEstado) {
            final List<Articulo> similarArticulos = articuloRepository.findSimilarArticulos(articulo);
            articuloDespachos.add(ArticuloDespacho.builder().articulo(articulo).cantidad(similarArticulos.size()).build());
        }
        if (articuloByEstado.isEmpty()) {
            return ArticuloDespachoResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return ArticuloDespachoResponse.builder().articuloDespachos(articuloDespachos).respuesta(Mensajes.OK.get()).build();
    }
    
    @PutMapping("/despachando/{id}")
    @PreAuthorize("hasAnyAuthority('ALL', 'REMOVE_DESPACHANDO')")
    public Respuesta removeArticuloFromDespacho(@PathVariable final Long id) {
        final Optional<Articulo> articulo = articuloRepository.findById(id);
        if (!articulo.isPresent()) {
            return Mensajes.NO_SE_ENCONTRARON_DATOS.get();
        }
        articulo.get().setEstado(EstadoArticulo.CREADO.get());
        articuloRepository.save(articulo.get());
        return Mensajes.OK.get();
    }
    
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ALL', 'REMOVE_ARTICULOS')")
    public Respuesta deleteArticulo(@PathVariable final Long id) {
        final Optional<Articulo> articulo = articuloRepository.findById(id);
        if (!articulo.isPresent()) {
            return Mensajes.NO_SE_ENCONTRARON_DATOS.get();
        }
        articuloMarcaModeloRepository.deleteByArticulo(articulo.get());
        articuloRepository.delete(articulo.get());
        return Mensajes.OK.get();
    }
}
