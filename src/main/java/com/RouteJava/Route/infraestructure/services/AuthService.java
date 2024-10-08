package com.RouteJava.Route.infraestructure.services;

import com.RouteJava.Route.api.dto.request.LoginReq;
import com.RouteJava.Route.api.dto.request.RegisterCarrierReq;
import com.RouteJava.Route.api.dto.request.RegisterReq;
import com.RouteJava.Route.api.dto.response.AuthResponse;
import com.RouteJava.Route.domain.entities.User;
import com.RouteJava.Route.domain.repositories.CarrierRepository;
import com.RouteJava.Route.infraestructure.abstract_services.IAuthService;
import com.RouteJava.Route.infraestructure.helpers.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private final User userRepository;
    @Autowired
    private final JwtService jwtService;
    //Interfaz que contiene los servicio de codificación
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final CarrierRepository carrierRepository;



    @Override
    public AuthResponse login(LoginReq request) {
        return null;
    }

    @Override
    public AuthResponse register(RegisterReq request) {
        return null;
    }

    @Override
    public AuthResponse registerEmployee(RegisterCarrierReq request) {
        return null;
    }
}
