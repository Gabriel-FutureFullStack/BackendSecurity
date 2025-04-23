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

import com.pixelpulse.oauth2resourse.model.Proveedores;
import com.pixelpulse.oauth2resourse.service.ProveedoresService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedoresRestController {
    private ProveedoresService proveedoresService;
    public ProveedoresRestController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }
    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Collection<Proveedores>> listar() {
        Collection<Proveedores> listProveedores = proveedoresService.getAll();
        return new ResponseEntity<>(listProveedores,HttpStatus.OK);
    }
    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Proveedores> obtenerUsuario(@PathVariable Long id) {
        Proveedores proveedorBd = proveedoresService.getById(id);
        if (proveedorBd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proveedorBd, HttpStatus.OK);
    }
    @PostMapping("/agregar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> agregar(@RequestBody Proveedores proveedor) {
		proveedoresService.insert(proveedor);		
		return new ResponseEntity<>("Proveedor Agregado Correctamente", HttpStatus.CREATED); 
	}
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Proveedores> editar(@PathVariable Long id, @RequestBody Proveedores newProveedor) {		
		proveedoresService.update(newProveedor, id);
        return ResponseEntity.ok(newProveedor);
	}
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        boolean deletedProveedor = proveedoresService.delete(id);
        if (deletedProveedor) {
            return ResponseEntity.ok("Proveedor eliminado Correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
