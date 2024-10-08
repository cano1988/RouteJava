package com.RouteJava.Route.domain.repositories;

import com.RouteJava.Route.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String > {

    public Optional<User> findByUserName(String username);
}
