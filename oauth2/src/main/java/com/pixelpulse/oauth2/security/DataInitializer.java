package com.pixelpulse.oauth2.security;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import com.pixelpulse.oauth2.service.RolesUsuariosService;
import com.pixelpulse.oauth2.service.UsuariosService;

@Component
public class DataInitializer implements CommandLineRunner{
    private UsuariosService userService;
    private RolesUsuariosService rolService;

    public DataInitializer(UsuariosService userService, RolesUsuariosService rolService) {
        this.userService = userService;
        this.rolService = rolService;
    }

    @Override
    public void run(String... args) throws Exception {
        rolService.createRolIfNotExists();
        userService.createUserIfNotExists();
    }
}
