package com.pixelpulse.oauth2resourse.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.pixelpulse.oauth2resourse.model.Proveedores;
import com.pixelpulse.oauth2resourse.repository.ProveedoresRepository;

@Service
public class ProveedoresServiceImpl implements ProveedoresService {
    private ProveedoresRepository proveedoresRepository;
    public ProveedoresServiceImpl(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }
    @Override
    public void insert(Proveedores entidad) {
        proveedoresRepository.save(entidad);
    }

    @Override
    public Proveedores update(Proveedores entidad, Long id) {
        Proveedores proveedorBd = getById(id);
        if (proveedorBd != null) {
            entidad.setIdProveedor(id);
            entidad.setNombreEmpresa(entidad.getNombreEmpresa() != null ? entidad.getNombreEmpresa() : proveedorBd.getNombreEmpresa());
            entidad.setContactoNombre(entidad.getContactoNombre() != null ? entidad.getContactoNombre() : proveedorBd.getContactoNombre());
            entidad.setTelefono(entidad.getTelefono() != null ? entidad.getTelefono() : proveedorBd.getTelefono());
            entidad.setCorreo(entidad.getCorreo() != null ? entidad.getCorreo() : proveedorBd.getCorreo());
            entidad.setDireccion(entidad.getDireccion() != null ? entidad.getDireccion() : proveedorBd.getDireccion());
            return proveedoresRepository.save(entidad);
        }else return entidad; 
    }

    @Override
    public boolean delete(Long id) {
        Proveedores proveedorBd = getById(id);
        if(proveedorBd != null){
            // proveedorBd.setEstado(false);
            // update(proveedorBd, id);
            proveedoresRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    public Proveedores getById(Long id) {
        return proveedoresRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Proveedores> getAll() {
        return proveedoresRepository.findAll();
    }
}
