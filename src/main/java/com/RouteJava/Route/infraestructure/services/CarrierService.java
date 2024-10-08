package com.RouteJava.Route.infraestructure.services;

import com.RouteJava.Route.api.dto.request.CarrierRequest;
import com.RouteJava.Route.api.dto.response.CarrierResp;
import com.RouteJava.Route.domain.entities.Carrier;
import com.RouteJava.Route.domain.repositories.CarrierRepository;
import com.RouteJava.Route.infraestructure.abstract_services.ICarrierService;
import com.RouteJava.Route.util.enums.SortType;
import com.RouteJava.Route.util.exceptions.BadRequestException;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CarrierService implements ICarrierService {

    @Autowired
    private final  CarrierRepository carrierRepository;


    @Override
    public CarrierResp create(CarrierRequest request) {
        Carrier carrier = this.toEntity(request);

        return this.toResponse(this.carrierRepository.save(carrier));
    }

    @Override
    public CarrierResp findById(Long id) {
        return this.toResponse(this.find(id));
    }

    private Carrier toEntity (CarrierRequest request){
        return Carrier.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    private CarrierResp toResponse(Carrier entity){
        return CarrierResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();


    }

    @Override
    public CarrierResp update(CarrierRequest request, Long id) {
        Carrier carrier = this.find(id);
        carrier = this.toEntity(request);
        carrier.setId(id);
        return this.toResponse(this.carrierRepository.save(carrier));
    }

    @Override
    public void delete(Long id) {
        Carrier carrier = this.find(id);
        this.carrierRepository.delete(carrier);

    }

    @Override
    public Page<CarrierResp> getAll(int page, int size, SortType sort) {
        if(page < 0 ) page = 0 ;
        PageRequest pagination = null;

        switch (sort) {
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size , Sort.by(FIELD_BY_SORT).descending());
            case NONE -> pagination = PageRequest.of(page, size);
        }
        return this.carrierRepository.findAll(pagination).map(this::toResponse);
    }

    private Carrier find(Long id){
        return this.carrierRepository.findById(id).orElseThrow(() -> new BadRequestException("Carrier no found"));
    }

}

