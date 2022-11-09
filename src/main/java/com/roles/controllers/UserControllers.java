package com.roles.controllers;

import com.roles.dto.LoginDTO;
import com.roles.dto.UserDTO;
import com.roles.entities.User;
import com.roles.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllers {

    @Autowired
    private UserServiceImpl userService;

    public UserControllers(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or('ROLE_USER')")
    @GetMapping("/index")
    public String user(){
        return "end point que se puede acceder desde cualquier usuario logueado";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> detele(@PathVariable Long id){

        userService.delete(id);
        return ResponseEntity.ok(userService.findAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/users")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO){
        if(userDTO.getId() == null){
            return ResponseEntity.notFound().build();
        }
        userService.update(userDTO);
        return ResponseEntity.ok(userDTO);

    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/users/softDelete")
    public ResponseEntity<?> findAllSoftDelete(){
        if(userService.findAllSoftdelete() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.findAllSoftdelete());
    }


}
