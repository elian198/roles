package com.roles.service.impl;

import com.roles.dto.UserDTO;
import com.roles.entities.Role;
import com.roles.entities.User;
import com.roles.entities.enums.RoleName;
import com.roles.repository.RoleRepository;
import com.roles.repository.UserRepository;
import com.roles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = userDTO.transformToUser();

        /**
         * todo creater the exceptions
         */
        if(repository.existsByEmail(user.getEmail())){

        }
        Role role = new Role();
        role.setName(RoleName.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        if(user.getEmail().split("@")[1].equals("admin.edu")){
            role.setName(RoleName.ADMIN);
            roles.add(role);

        }

        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(String username) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
