package com.roles.service;

import com.roles.dto.UserDTO;
import com.roles.entities.User;

import java.util.List;

public interface UserService {
    User save(UserDTO user);
    List<User> findAll();
    User findOne(String username);
    User findById(Long id);
}
