package com.pixelpulse.oauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixelpulse.oauth2.model.Usuarios;
import com.pixelpulse.oauth2.service.UsuariosService;

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
@RequestMapping("/api/usuarios")
public class UsuariosRestController {
    private UsuariosService userService;
    public UsuariosRestController(UsuariosService userService) {
        this.userService = userService;
    }
    //Get all users
    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Collection<Usuarios>> listar() {
        Collection<Usuarios> listUsers = userService.getAll();
        return new ResponseEntity<>(listUsers,HttpStatus.OK);
    }
    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Usuarios> obtenerUsuario(@PathVariable Long id) {
        Usuarios userBd = userService.getById(id);
        if (userBd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userBd, HttpStatus.OK);
    }
    @PostMapping("/agregar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> agregar(@RequestBody Usuarios usuario) {
		userService.insert(usuario);		
		return new ResponseEntity<>("Usuario Agregado Correctamente", HttpStatus.CREATED); 
	}
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Usuarios> editar(@PathVariable Long id, @RequestBody Usuarios newUser) {		
		userService.update(newUser, id);
        return ResponseEntity.ok(newUser);
	}
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        boolean deletedUser = userService.delete(id);
        if (deletedUser) {
            return ResponseEntity.ok("Usuario eliminado Correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
