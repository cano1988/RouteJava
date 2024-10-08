package com.RouteJava.Route.infraestructure.abstract_services;

import com.RouteJava.Route.api.dto.request.LoginReq;
import com.RouteJava.Route.api.dto.request.RegisterCarrierReq;
import com.RouteJava.Route.api.dto.request.RegisterReq;
import com.RouteJava.Route.api.dto.response.AuthResponse;

public interface IAuthService {


    public AuthResponse login(LoginReq request);

    public AuthResponse register(RegisterReq request);
    public AuthResponse registerEmployee(RegisterCarrierReq request);

}
