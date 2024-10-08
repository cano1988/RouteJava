package com.RouteJava.Route.infraestructure.abstract_services;

import com.RouteJava.Route.api.dto.request.LoadRequest;
import com.RouteJava.Route.api.dto.response.LoadResp;

public interface ILoadService
        extends CrudService<LoadRequest, LoadResp, Long> {
    public final String FIELD_BY_SORT = "weigh";


}
