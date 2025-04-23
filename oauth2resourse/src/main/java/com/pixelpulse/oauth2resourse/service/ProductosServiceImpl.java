package com.pixelpulse.oauth2resourse.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.pixelpulse.oauth2resourse.model.Productos;
import com.pixelpulse.oauth2resourse.repository.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService{

    private ProductosRepository productosRepository;
    private ProveedoresService proveedoresService;
    public ProductosServiceImpl(ProductosRepository productosRepository, ProveedoresService proveedoresService) {
        this.productosRepository = productosRepository;
        this.proveedoresService = proveedoresService;
    }
    @Override
    public void insert(Productos entidad) {
        productosRepository.save(entidad);
    }

    @Override
    public Productos update(Productos entidad, Long id) {
        Productos productoBd = getById(id);
        if (productoBd != null) {
            entidad.setIdProducto(id);
            entidad.setNombre(entidad.getNombre() != null ? entidad.getNombre() : productoBd.getNombre());
            entidad.setDescripcion(entidad.getDescripcion() != null ? entidad.getDescripcion() : productoBd.getDescripcion());
            entidad.setPrecioUnitario(entidad.getPrecioUnitario() != null ? entidad.getPrecioUnitario() : productoBd.getPrecioUnitario());
            entidad.setProveedores(entidad.getProveedores()!= null? proveedoresService.getById(entidad.getProveedores().getIdProveedor()) : productoBd.getProveedores());
            entidad.setEstado(entidad.isEstado());
            return productosRepository.save(entidad);
        }else return entidad;
    }

    @Override
    public boolean delete(Long id) {
        Productos productoBd = getById(id);
        if(productoBd != null){
            productoBd.setEstado(false);
            update(productoBd, id);
            // productosRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    public Productos getById(Long id) {
        return productosRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Productos> getAll() {
        return productosRepository.findAll();
    }

}
