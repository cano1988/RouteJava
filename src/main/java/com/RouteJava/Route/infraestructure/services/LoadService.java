package com.RouteJava.Route.infraestructure.services;

import com.RouteJava.Route.api.dto.request.CarrierRequest;
import com.RouteJava.Route.api.dto.request.LoadRequest;
import com.RouteJava.Route.api.dto.response.CarrierResp;
import com.RouteJava.Route.api.dto.response.LoadResp;
import com.RouteJava.Route.domain.entities.Carrier;
import com.RouteJava.Route.domain.entities.Load;
import com.RouteJava.Route.domain.repositories.CarrierRepository;
import com.RouteJava.Route.domain.repositories.LoadRepository;
import com.RouteJava.Route.infraestructure.abstract_services.ILoadService;
import com.RouteJava.Route.util.enums.SortType;
import com.RouteJava.Route.util.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoadService implements ILoadService {

    @Autowired
    private final LoadRepository loadRepository;


    @Override
    public LoadResp create(LoadRequest request) {
        Load load = this.toEntity(request);

        return this.toResponse(this.loadRepository.save(load));
    }

    private Load toEntity (LoadRequest request){
        return Load.builder()
                .weigh(request.getWeigh())
                .date(request.getDate())
                .dimension(request.getDimension())
                .description(request.getDescription())
                .state(request.getState())
                .build();
    }

    private LoadResp toResponse(Load entity){
        return LoadResp.builder()
                .id(entity.getId())
                .weigh(entity.getWeigh())
                .date(entity.getDate())
                .dimension(entity.getDimension())
                .description(entity.getDescription())
                .state(entity.getState())
                .build();


    }

    @Override
    public LoadResp findById(Long id) {
        return this.toResponse(this.find(id));
    }

    @Override
    public LoadResp update(LoadRequest request, Long id) {
        Load load = this.find(id);
        load = this.toEntity(request);
        load.setId(id);
        return this.toResponse(this.loadRepository.save(load));
    }

    @Override
    public void delete(Long id ) {
        Load load = this.find(id);
        this.loadRepository.delete(load);
    }

    @Override
    public Page<LoadResp> getAll(int page, int size, SortType sort) {
        if(page < 0 ) page = 0 ;
        PageRequest pagination = null;

        switch (sort) {
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size , Sort.by(FIELD_BY_SORT).descending());
            case NONE -> pagination = PageRequest.of(page, size);
        }
        return this.loadRepository.findAll(pagination).map(this::toResponse);
    }

    private Load find(Long id){
        return this.loadRepository.findById(id).orElseThrow(() -> new BadRequestException("Load no found"));
    }
}
