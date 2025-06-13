package com.yuri.login.repository;

import com.yuri.login.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(attributePaths = {"role"}) // Configura para carregar o atributo "role"
    Optional<User> findByNameOrEmail(String name, String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}