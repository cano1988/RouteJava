package com.RouteJava.Route.domain.repositories;

import com.RouteJava.Route.domain.entities.Pallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalletRepository
        extends JpaRepository<Pallet, Long> {
}
