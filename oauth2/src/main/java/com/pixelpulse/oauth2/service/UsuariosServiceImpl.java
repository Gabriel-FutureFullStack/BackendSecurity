package com.pixelpulse.oauth2.service;

import java.util.Collection;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pixelpulse.oauth2.model.Usuarios;
import com.pixelpulse.oauth2.repository.UsuariosRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuariosServiceImpl implements UsuariosService{
    //Instaciar el PasswordEncoder para encriptar la contraseña
    private final PasswordEncoder passwordEncoder;
    //Instanciar el repositorio de usuarios
    private UsuariosRepository userRepository;
    //Instanciar el servicio de roles
    private RolesUsuariosService rolesUsuariosService;

    public UsuariosServiceImpl(UsuariosRepository userRepository, RolesUsuariosService rolesUsuariosService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesUsuariosService = rolesUsuariosService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    @Transactional
    public void insert(Usuarios entidad) {
        entidad.setPassword(passwordEncoder.encode(entidad.getPassword()));
        userRepository.save(entidad);
    }

    @Override
    @Transactional
    public Usuarios update(Usuarios entidad, Long id) {
        Usuarios userBd = getById(id);
        if (userBd != null) {
            entidad.setIdUsuario(id);
            entidad.setNombre(entidad.getNombre() != null? entidad.getNombre() : userBd.getNombre());
            entidad.setCorreo(entidad.getCorreo()!= null? entidad.getCorreo() : userBd.getCorreo());
            entidad.setUsername(entidad.getUsername()!= null? entidad.getUsername() : userBd.getUsername());
            entidad.setPassword(entidad.getPassword()!= null? passwordEncoder.encode(entidad.getPassword()) : passwordEncoder.encode(userBd.getPassword()));
            // entidad.setRolesUsuarios(entidad.getRolesUsuarios()!= null? entidad.getRolesUsuarios() : userBd.getRolesUsuarios());
            entidad.setRolesUsuarios(entidad.getRolesUsuarios()!= null? rolesUsuariosService.getById(entidad.getRolesUsuarios().getIdRol()) : userBd.getRolesUsuarios());
            entidad.setEstado(entidad.isEstado());
            return userRepository.save(entidad);
        }
        return entidad;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<Usuarios> userBd = userRepository.findById(id);
        if(userBd.isPresent()){
            // Eliminacion de usuario logico
            Usuarios userEliminado = userBd.get();
            userEliminado.setEstado(false);
            update(userEliminado, id);
            // Eliminacion de usuario fisico
            // userRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    @Transactional
    public Usuarios getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Collection<Usuarios> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void createUserIfNotExists() {
        if(userRepository.count() == 0){
            // Crear el rol de administrador si no existe
            Usuarios userAdmin = new Usuarios();
            userAdmin.setNombre("Gabriel Rosas Ortiz");
            userAdmin.setCorreo("gero6630@gmail.com");
            userAdmin.setUsername("admin");
            userAdmin.setPassword("admin");
            userAdmin.setEstado(true);
            userAdmin.setRolesUsuarios(rolesUsuariosService.getById(1L));
            insert(userAdmin);  

            Usuarios user = new Usuarios();
            user.setNombre("Gabriel Rosas Ortiz");
            user.setCorreo("gero6630@gmail.com");
            user.setUsername("user");
            user.setPassword("user");
            user.setEstado(true);
            user.setRolesUsuarios(rolesUsuariosService.getById(2L));
            insert(user);  
        }
    }


}
