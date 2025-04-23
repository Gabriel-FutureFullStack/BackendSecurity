package com.pixelpulse.oauth2.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.pixelpulse.oauth2.model.RolesUsuarios;
import com.pixelpulse.oauth2.repository.RolesUsuariosRepository;

import jakarta.transaction.Transactional;

@Service
public class RolesUsuariosServiceImpl implements RolesUsuariosService {
    private RolesUsuariosRepository rolRepository;
    public RolesUsuariosServiceImpl(RolesUsuariosRepository rolRepository){
        this.rolRepository = rolRepository;
    }
    
    @Override
    @Transactional
    public void insert(RolesUsuarios entidad) {
        rolRepository.save(entidad);
    }

    @Override
    @Transactional
    public RolesUsuarios update(RolesUsuarios entidad, Long id) {
        RolesUsuarios rolBd = getById(id);
        if (rolBd != null) {
            entidad.setIdRol(id);
            entidad.setNombreRol(entidad.getNombreRol() != null? entidad.getNombreRol() : rolBd.getNombreRol());
            return rolRepository.save(entidad);
        }
        return entidad;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<RolesUsuarios> rolBd = rolRepository.findById(id);
        if(rolBd.isPresent()){
            rolRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    @Transactional
    public RolesUsuarios getById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Collection<RolesUsuarios> getAll() {
        return rolRepository.findAll();
    }
    @Override
    public void createRolIfNotExists() {
        if(rolRepository.count() == 0){
            RolesUsuarios rolAdmin = new RolesUsuarios();
            rolAdmin.setNombreRol("ROLE_ADMIN");
            rolRepository.save(rolAdmin);
            
            RolesUsuarios rolUser = new RolesUsuarios();
            rolUser.setNombreRol("ROLE_USER");
            rolRepository.save(rolUser);
            
            RolesUsuarios rolGuest = new RolesUsuarios();
            rolGuest.setNombreRol("ROLE_GUEST");
            rolRepository.save(rolGuest);
        }
    }
}
