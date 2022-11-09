package com.roles.repository;

import com.roles.dto.LoginDTO;
import com.roles.dto.UserDTO;
import com.roles.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE users.user_name = :filtro" ,nativeQuery = true)
    User findByUsername(@Param("filtro") String filtro);

    @Query(value = "SELECT user_name FROM users WHERE users.user_name = :filtro" ,nativeQuery = true)
    String existsByUsername(String filtro);

    @Query(value = "SELECT * FROM users WHERE users.soft_delete = true" ,nativeQuery = true)
    List<User> findAllUserSoftDelete();

    boolean existsByEmail(String email);

}
