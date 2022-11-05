package com.roles.controllers;

import com.roles.dto.UserDTO;
import com.roles.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllers {

    @Autowired
    private UserServiceImpl userService;

    public UserControllers(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
        return "Welcome to the App roles";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){

        userService.save(userDTO);
        return ResponseEntity.ok("Usuario creado con exito!!");
    }
}
