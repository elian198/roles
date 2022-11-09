package com.roles.service.impl;

import com.roles.entities.Role;
import com.roles.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsMapper {
    public static UserDetails build(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassowrd(), getAuthorities(user));
    }
    private static Set<? extends GrantedAuthority> getAuthorities(User retrievedUser) {
        Set<Role> roles = retrievedUser.getRoles();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }
}
