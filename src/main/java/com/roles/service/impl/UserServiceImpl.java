package com.roles.service.impl;

import com.roles.dto.UserDTO;
import com.roles.entities.Role;
import com.roles.entities.User;
import com.roles.entities.enums.RoleName;
import com.roles.exception.EmailAlreadyExistException;
import com.roles.exception.UserNameAlreadyExistsException;
import com.roles.repository.RoleRepository;
import com.roles.repository.UserRepository;
import com.roles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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


        if(repository.existsByEmail(user.getEmail())){
             throw new EmailAlreadyExistException("Email ocupado, elija otro por favor");
        }

        Role role = new Role();
        role.setName(RoleName.USER);
        role.setDescription("NORMAL");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if(user.getEmail().split("@")[1].equals("admin.edu")){
            role.setName(RoleName.ADMIN);
            role.setDescription("ADMIN");
            roleSet.add(role);

        }
        user.setRoles(roleSet);
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return  repository.findAll();
    }



    public Boolean findByUserName(String name){
        if(repository.existsByUsername(name) != null){
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public Boolean findByEmail(String email){
        if(!repository.existsByEmail(email)){
            return false;
        }
        return true;
    }
}



