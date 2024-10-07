package com.RouteJava.Route.domain.repositories;

import com.RouteJava.Route.domain.entities.Load;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadRepository
        extends JpaRepository<Load, Long> {
}
