package com.pixelpulse.oauth2resourse.service;

import java.util.Collection;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.pixelpulse.oauth2resourse.model.Compras;
import com.pixelpulse.oauth2resourse.model.DetalleCompras;
import com.pixelpulse.oauth2resourse.model.DetalleComprasKey;
import com.pixelpulse.oauth2resourse.model.Productos;
import com.pixelpulse.oauth2resourse.repository.ComprasRepository;

import jakarta.transaction.Transactional;

@Service
public class ComprasServiceImpl implements ComprasService {
    private ComprasRepository comprasRepository;
    private ProductosService productoService;

    public ComprasServiceImpl(ComprasRepository comprasRepository, ProductosService productoService) {
        this.comprasRepository = comprasRepository;
        this.productoService = productoService;
    }
    
    @Override
    @Transactional
    public void insert(Compras entidad) {
        // 1. Guardar la entidad para obtener el idCompra generado sin detalles
        entidad.setTotal(0.0);
        Set<DetalleCompras> detalles = entidad.getDetalleCompras();
        entidad.setDetalleCompras(null);
        Compras compraGuardada = comprasRepository.save(entidad);
        double total = 0.0;
        // 2. Recorrer cada detalle de entidad
        for (DetalleCompras detalle : detalles) {
            // Asignar la entidad al detalle
            detalle.setCompras(compraGuardada);

            // Buscar el producto en base al id que vino en el JSON
            Long idProd = detalle.getProductos().getIdProducto();
            Productos producto = productoService.getById(idProd);

            // Asignar la entidad producto completa (con precio actualizado)
            detalle.setProductos(producto);
            
            // Si el precio unitario no viene o querés usar el precio real, lo asignás:
            detalle.setPrecioUnitario(producto.getPrecioUnitario());
            
            // Crear o actualizar la clave compuesta
            DetalleComprasKey key = new DetalleComprasKey();
            key.setIdCompra(compraGuardada.getIdCompra());
            key.setIdProducto(producto.getIdProducto());
            detalle.setId(key);
            
            // Calcular subtotal
            double subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
            detalle.setSubtotal(subtotal);
            total += subtotal;
        }
        compraGuardada.setDetalleCompras(detalles);
        // 3. Actualizar el total en la entidad y guardar nuevamente
        compraGuardada.setTotal(total);
        comprasRepository.save(compraGuardada);
    }

    @Override
    @Transactional
    public Compras update(Compras entidad, Long id) {
        double total = 0.0;
        entidad.setTotal(0.0);
        Compras comprasBd = getById(id);
        Set<DetalleCompras> detalles = entidad.getDetalleCompras();
        entidad.setIdCompra(id);
        entidad.setIdUsuario(entidad.getIdUsuario() != null ? entidad.getIdUsuario() : comprasBd.getIdUsuario());
        entidad.setFechaCompra(comprasBd.getFechaCompra());
        // 1. Recorrer cada detalle de entidad
        for (DetalleCompras detalle : detalles) {
            // Asignar la entidad al detalle
            detalle.setCompras(comprasBd);
            // Buscar el producto en base al id que vino en el JSON
            Long idProd = detalle.getProductos().getIdProducto();
            Productos producto = productoService.getById(idProd);
            // Asignar la entidad producto completa (con precio actualizado)
            detalle.setProductos(producto);
            // Si el precio unitario no viene o querés usar el precio real, lo asignás:
            detalle.setPrecioUnitario(producto.getPrecioUnitario());
            // Crear o actualizar la clave compuesta
            DetalleComprasKey key = new DetalleComprasKey();
            key.setIdCompra(comprasBd.getIdCompra());
            key.setIdProducto(producto.getIdProducto());
            detalle.setId(key);
            // Calcular subtotal
            double subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
            detalle.setSubtotal(subtotal);
            total += subtotal;
        }
        entidad.setTotal(total);
        entidad.setDetalleCompras(detalles);
        return comprasRepository.save(entidad);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Compras comprasBd = getById(id);
        if(comprasBd != null){
            comprasRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    @Transactional
    public Compras getById(Long id) {
        return comprasRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Collection<Compras> getAll() {
       return comprasRepository.findAll();
    }
}
