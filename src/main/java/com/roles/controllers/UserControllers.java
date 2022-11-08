package com.roles.controllers;

import com.roles.dto.AuthToken;
import com.roles.dto.LoginDTO;
import com.roles.dto.UserDTO;
import com.roles.security.JwtResponse;
import com.roles.security.payload.jwt.JwtTokenUtil;
import com.roles.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserControllers {


    private UserServiceImpl userService;

    private AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserControllers(UserServiceImpl userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index(){
        return "Welcome to the App roles";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){

        if(userService.findByEmail(userDTO.getEmail())){
           return ResponseEntity.badRequest().body("El email ya existe!!");
        }

        if(userService.findByUserName(userDTO.getUserName())){
            return ResponseEntity.badRequest().body("El usuario ya existe!!");
        }
        String password = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password);
        userService.save(userDTO);
        return ResponseEntity.ok("Usuario creado con exito!!");
    }

   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
       final Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                    loginDTO.getUserName(),
                       loginDTO.getPassword()
               )
       );
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String token = jwtTokenUtil.generateJwtToken(authentication);
       System.out.println(new JwtResponse(token));
       UserDetails userDetails = (UserDetails) authentication.getPrincipal();

       return ResponseEntity.ok(new AuthToken(token));
   }
}
