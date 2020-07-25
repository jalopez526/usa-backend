package com.usa.controller;

import com.usa.util.Endpoint;
import org.springframework.web.bind.annotation.GetMapping;
import com.usa.model.dto.despacho.GetDespachoResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Set;
import java.util.Optional;
import java.util.Iterator;
import org.springframework.security.core.Authentication;
import com.usa.model.domain.despacho.DespachoDetalle;
import com.usa.enums.EstadoArticulo;
import java.util.HashSet;
import java.util.Collections;
import java.util.function.Function;
import java.util.Comparator;
import com.usa.model.domain.articulo.Articulo;
import com.usa.enums.Mensajes;
import java.util.Date;
import com.usa.model.domain.despacho.Despacho;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import com.usa.model.dto.despacho.CreateDespachoResponse;
import org.springframework.web.bind.annotation.RequestBody;
import com.usa.model.dto.despacho.ArticuloDespacho;
import java.util.List;
import com.usa.repository.ArticuloRepository;
import com.usa.repository.UsuarioRepository;
import com.usa.repository.DespachoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.usa.repository.DespachoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.despachos)
@CrossOrigin
public class DespachoController
{
    @Autowired
    private DespachoRepository despachoRepository;
    @Autowired
    private DespachoDetalleRepository despachoDetalleRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ALL', 'DESPACHAR')")
    public CreateDespachoResponse postDespacho(@RequestBody final List<ArticuloDespacho> articuloDespachos) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetail = (UserDetails) authentication.getPrincipal();

        final Despacho despacho = new Despacho();
        despacho.setUsuario(usuarioRepository.findByUsuario(userDetail.getUsername()));
        despacho.setFechaCreacion(new Date());
        final Despacho newDespacho = despachoRepository.save(despacho);

        for (final ArticuloDespacho articuloDespacho : articuloDespachos) {
            final Optional<Articulo> articuloOptional = articuloRepository.findById(Long.valueOf(articuloDespacho.getId()));
            if (!articuloOptional.isPresent()) {
                return CreateDespachoResponse.builder().respuesta(Mensajes.DATOS_INCORRECTOS.get()).build();
            }
            final Articulo articulo = articuloOptional.get();
            final List<Articulo> similarArticulos = articuloRepository.findSimilarArticulos(articulo);
            final int cantidadArticulosADespachar = Integer.parseInt(articuloDespacho.getCantidad());

            if (similarArticulos.size() == 0 || cantidadArticulosADespachar > similarArticulos.size()) {
                return CreateDespachoResponse.builder().respuesta(Mensajes.CANTIDAD_ARTICULOS_NO_DISPONIBLE.get()).build();
            }
            similarArticulos.sort(Comparator.comparing(Articulo::getFechaCreacion));
            Collections.reverse(similarArticulos);
            final Set<Articulo> articulos = new HashSet<>();
            articulos.add(articulo);
            for (final Articulo a : similarArticulos) {
                if (articulos.size() >= cantidadArticulosADespachar) {
                    break;
                }
                articulos.add(a);
            }
            
            articulos.forEach(f -> {
                f.setEstado(EstadoArticulo.DESPACHADO.get());
                articuloRepository.save(f);                
                DespachoDetalle despachoDetalle = new DespachoDetalle();
                despachoDetalle.setDespacho(newDespacho);
                despachoDetalle.setArticulo(f);
                despachoDetalleRepository.save(despachoDetalle);
            });
        }
        return CreateDespachoResponse.builder().respuesta(Mensajes.OK.get()).despacho(newDespacho).build();
    }
    
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ALL', 'READ_DESPACHO')")
    public GetDespachoResponse getDespacho() {
        final List<Despacho> despachos = despachoRepository.findAll();
        if (despachos.isEmpty()) {
            return GetDespachoResponse.builder().respuesta(Mensajes.NO_SE_ENCONTRARON_DATOS.get()).build();
        }
        return GetDespachoResponse.builder().respuesta(Mensajes.OK.get()).despachos(despachos).build();
    }
}
