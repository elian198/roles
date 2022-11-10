package com.roles.controllers;

import com.roles.dto.LoginDTO;
import com.roles.dto.UserDTO;
import com.roles.entities.User;
import com.roles.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllers {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserControllers(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ApiOperation("End point que se puede acceder siendo usuario USER o ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN') or('ROLE_USER')")
    @GetMapping("/index")
    public String user(){
        return "end point que se puede acceder desde cualquier usuario logueado";
    }

    @ApiOperation("Muestra todos los usuarios regitrados que esten activos")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @ApiOperation("Elimina un usuario de manera logica ingredando el id")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> detele(@PathVariable Long id){

        return ResponseEntity.ok(userService.delete(id));
    }
    @ApiOperation("Actualiza un usuario")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/users")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO){
        if(userDTO.getId() == null){
            return ResponseEntity.notFound().build();
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.update(userDTO);
        return ResponseEntity.ok(userDTO);

    }
    @ApiOperation("Muestra los usuarios inactivos y solamente un usuario logueado como administrador puede verlos")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/users/softDelete")
    public ResponseEntity<?> findAllSoftDelete(){
        if(userService.findAllSoftdelete() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.findAllSoftdelete());
    }
}
