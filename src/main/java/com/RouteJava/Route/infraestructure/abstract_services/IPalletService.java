package com.RouteJava.Route.infraestructure.abstract_services;

import com.RouteJava.Route.api.dto.request.PalletRequest;
import com.RouteJava.Route.api.dto.response.PalletResp;

public interface IPalletService
        extends CrudService<PalletRequest, PalletResp,Long> {
    public final String FIELD_BY_SORT = "capacity";
}
