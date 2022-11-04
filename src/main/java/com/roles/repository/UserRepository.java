package com.roles.repository;

import com.roles.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT *  FROM Users WHERE USers.user_name = :filtro" ,nativeQuery = true)
    User findByUsername(String filtro);

    boolean existsByEmail(String email);
}
