package com.roles.service.impl;

import com.roles.dto.LoginDTO;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {

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


        if (repository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException("Email ocupado, elija otro por favor");
        }

        Role role = new Role();
        role.setName(RoleName.USER);
        role.setDescription("NORMAL");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if (user.getEmail().split("@")[1].equals("admin.edu")) {
            role.setName(RoleName.ADMIN);
            role.setDescription("ADMIN");
            roleSet.add(role);

        }
        user.setRoles(roleSet);
        user.setSorfDelete(false);
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }


    public Boolean findByUserName(String name) {
        if (repository.existsByUsername(name) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public Boolean findByEmail(String email) {
        if (!repository.existsByEmail(email)) {
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        com.roles.entities.User user = repository.findByUsername(nombre);

        return UserDetailsMapper.build(user);
    }


    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> delete(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return repository.findAll();
        }
        throw new UserNameAlreadyExistsException("El id no existe");
    }

    /**
     * todo falta terminar
     * @param userDTO
     * @return userDTO
     */
    public UserDTO update(UserDTO userDTO){
        User user = userDTO.transformToUser();
        if(!repository.existsById(user.getId())){
            throw new UserNameAlreadyExistsException("El id no existe");
        }

        save(userDTO);
        return userDTO;
    }

    public List<User> findAllSoftdelete(){
        return repository.findAllUserSoftDelete();
    }


}



