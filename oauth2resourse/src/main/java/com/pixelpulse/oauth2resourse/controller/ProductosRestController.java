package com.pixelpulse.oauth2resourse.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixelpulse.oauth2resourse.model.Productos;
import com.pixelpulse.oauth2resourse.service.ProductosService;

@RestController
@RequestMapping("/api/productos")
public class ProductosRestController {
    private ProductosService productosService;
    public ProductosRestController(ProductosService productosService) {
        this.productosService = productosService;
    }
    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Collection<Productos>> listar() {
        Collection<Productos> listProductos = productosService.getAll();
        return new ResponseEntity<>(listProductos,HttpStatus.OK);
    }
    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Productos> obtenerUsuario(@PathVariable Long id) {
        Productos productoBd = productosService.getById(id);
        if (productoBd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productoBd, HttpStatus.OK);
    }
    @PostMapping("/agregar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> agregar(@RequestBody Productos producto) {
		productosService.insert(producto);
		return new ResponseEntity<>("Producto Agregado Correctamente", HttpStatus.CREATED); 
	}
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Productos> editar(@PathVariable Long id, @RequestBody Productos newProducto) {		
		productosService.update(newProducto, id);
        return ResponseEntity.ok(newProducto);
	}
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        boolean deletedProducto = productosService.delete(id);
        if (deletedProducto) {
            return ResponseEntity.ok("Producto eliminado Correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
