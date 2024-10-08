package com.RouteJava.Route.infraestructure.abstract_services;

import com.RouteJava.Route.util.enums.SortType;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CrudService <RQ, RS, ID> {

    public RS create(RQ request);

    public RS findById(ID id);

    public RS update(RQ request, ID id);

    public void delete(ID id);

    public Page<RS> getAll(int page, int size, SortType sort);
}
