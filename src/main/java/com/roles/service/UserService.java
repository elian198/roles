package com.roles.service;

import com.roles.dto.UserDTO;
import com.roles.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(UserDTO user);
    List<User> findAll();
    User findOne(String username);
    Optional<User> findById(Long id);
}
