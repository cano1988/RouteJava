package com.RouteJava.Route.domain.repositories;


import com.RouteJava.Route.domain.entities.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository
        extends JpaRepository<Carrier, Long> {
}
