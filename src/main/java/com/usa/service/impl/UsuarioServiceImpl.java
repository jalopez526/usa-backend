package com.usa.service.impl;

import com.usa.model.domain.login.Usuario;
import com.usa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public UserDetails loadUserByUsername(final String s) {
        final Usuario usuario = usuarioRepository.findByUsuario(s);
        if (usuario == null) {
            throw new UsernameNotFoundException("The entered user was not found");
        }
        final List<GrantedAuthority> authorities = new ArrayList<>();
        usuario.getRol().getRolPermisos().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getPermiso().getDescripcion())));
        return new User(usuario.getUsuario(), usuario.getPassword(), authorities);
    }
}
