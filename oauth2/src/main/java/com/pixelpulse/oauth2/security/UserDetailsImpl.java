package com.pixelpulse.oauth2.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.pixelpulse.oauth2.model.Usuarios;

public class UserDetailsImpl implements UserDetails{
    private final Usuarios usuario;
    
    public UserDetailsImpl(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String rol = usuario.getRolesUsuarios().getNombreRol();
        return Collections.singleton(new SimpleGrantedAuthority(rol));
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return usuario.isEstado();
    }

    public Usuarios getUsuario() {
        return usuario;
    }
}
