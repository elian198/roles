package com.roles.service.impl;

import com.roles.entities.Role;
import com.roles.repository.RoleRepository;
import com.roles.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;



    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
