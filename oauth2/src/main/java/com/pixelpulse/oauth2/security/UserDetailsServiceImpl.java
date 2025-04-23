package com.pixelpulse.oauth2.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pixelpulse.oauth2.model.Usuarios;
import com.pixelpulse.oauth2.repository.UsuariosRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private UsuariosRepository userRepository;

    public UserDetailsServiceImpl(UsuariosRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UserDetailsImpl(usuario);
    }
}
