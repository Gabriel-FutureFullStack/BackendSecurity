package com.pixelpulse.oauth2resourse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixelpulse.oauth2resourse.model.Compras;
import com.pixelpulse.oauth2resourse.service.ComprasService;

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


@RestController
@RequestMapping("/api/compras")
public class ComprasRestController {
    private ComprasService comprasService;
    public ComprasRestController(ComprasService comprasService) {
        this.comprasService = comprasService;
    }
    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Collection<Compras>> listar() {
        Collection<Compras> listCompras = comprasService.getAll();
        return new ResponseEntity<>(listCompras,HttpStatus.OK);
    }
    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Compras> obtenerUsuario(@PathVariable Long id) {
        Compras compraBd = comprasService.getById(id);
        if (compraBd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(compraBd, HttpStatus.OK);
    }
    @PostMapping("/agregar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> agregar(@RequestBody Compras compra) {
		comprasService.insert(compra);		
		return new ResponseEntity<>("Compra Agregada Correctamente", HttpStatus.CREATED); 
	}
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Compras> editar(@PathVariable Long id, @RequestBody Compras newCompra) {		
		comprasService.update(newCompra, id);
        return ResponseEntity.ok(newCompra);
	}
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        boolean deletedCompra = comprasService.delete(id);
        if (deletedCompra) {
            return ResponseEntity.ok("Compra eliminada Correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
