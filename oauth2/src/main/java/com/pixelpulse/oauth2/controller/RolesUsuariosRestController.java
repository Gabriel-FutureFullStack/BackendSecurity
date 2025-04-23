package com.pixelpulse.oauth2.controller;

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

import com.pixelpulse.oauth2.model.RolesUsuarios;
import com.pixelpulse.oauth2.service.RolesUsuariosService;

@RestController
@RequestMapping("/api/roles")
public class RolesUsuariosRestController {
    private RolesUsuariosService rolesUsuariosService;
    public RolesUsuariosRestController(RolesUsuariosService rolesUsuariosService) {
        this.rolesUsuariosService = rolesUsuariosService;
    }
    //Get all users
    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Collection<RolesUsuarios>> listar() {
        Collection<RolesUsuarios> listRoles = rolesUsuariosService.getAll();
        return new ResponseEntity<>(listRoles,HttpStatus.OK);
    }
    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<RolesUsuarios> obtenerUsuario(@PathVariable Long id) {
        RolesUsuarios rolBd = rolesUsuariosService.getById(id);
        if (rolBd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rolBd, HttpStatus.OK);
    }
    @PostMapping("/agregar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> agregar(@RequestBody RolesUsuarios rol) {
		rolesUsuariosService.insert(rol);		
		return new ResponseEntity<>("Rol Agregado Correctamente", HttpStatus.CREATED); 
	}
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<RolesUsuarios> editar(@PathVariable Long id, @RequestBody RolesUsuarios newRol) {		
		rolesUsuariosService.update(newRol, id);
        return ResponseEntity.ok(newRol);
	}
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        boolean deletedRol = rolesUsuariosService.delete(id);
        if (deletedRol) {
            return ResponseEntity.ok("Rol eliminado Correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
