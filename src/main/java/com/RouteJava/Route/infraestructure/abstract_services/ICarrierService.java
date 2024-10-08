package com.RouteJava.Route.infraestructure.abstract_services;

import com.RouteJava.Route.api.dto.request.CarrierRequest;
import com.RouteJava.Route.api.dto.response.CarrierResp;
import jakarta.persistence.Id;

public interface ICarrierService
        extends CrudService<CarrierRequest, CarrierResp, Long>{
        public final String FIELD_BY_SORT = "name";


}
