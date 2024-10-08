package com.RouteJava.Route.infraestructure.services;

import com.RouteJava.Route.api.dto.request.LoadRequest;
import com.RouteJava.Route.api.dto.request.PalletRequest;
import com.RouteJava.Route.api.dto.response.LoadResp;
import com.RouteJava.Route.api.dto.response.PalletResp;
import com.RouteJava.Route.domain.entities.Load;
import com.RouteJava.Route.domain.entities.Pallet;
import com.RouteJava.Route.domain.repositories.LoadRepository;
import com.RouteJava.Route.domain.repositories.PalletRepository;
import com.RouteJava.Route.infraestructure.abstract_services.IPalletService;
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
public class PalletService implements IPalletService {


    @Autowired
    private final PalletRepository palletRepository;

    @Override
    public PalletResp create(PalletRequest request) {
        Pallet pallet = this.toEntity(request);

        return this.toResponse(this.palletRepository.save(pallet));
    }

    private Pallet toEntity (PalletRequest request){
        return Pallet.builder()
                .capacity(request.getCapacity())
                .location(request.getLocation())
                .material(request.getMaterial())
                .state(request.getState())
                .build();
    }

    private PalletResp toResponse(Pallet entity){
        return PalletResp.builder()
                .id(entity.getId())
                .capacity(entity.getCapacity())
                .location(entity.getLocation())
                .material(entity.getMaterial())
                .state(entity.getState())
                .build();


    }

    @Override
    public PalletResp findById(Long id) {
        return this.toResponse(this.find(id));
    }

    @Override
    public PalletResp update(PalletRequest request, Long id) {
        Pallet pallet = this.find(id);
        pallet = this.toEntity(request);
        pallet.setId(id);
        return this.toResponse(this.palletRepository.save(pallet));
    }

    @Override
    public void delete(Long id) {
        Pallet pallet= this.find(id);
        this.palletRepository.delete(pallet);

    }

    @Override
    public Page<PalletResp> getAll(int page, int size, SortType sort) {
        if(page < 0 ) page = 0 ;
        PageRequest pagination = null;

        switch (sort) {
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size , Sort.by(FIELD_BY_SORT).descending());
            case NONE -> pagination = PageRequest.of(page, size);
        }
        return this.palletRepository.findAll(pagination).map(this::toResponse);
    }

    private Pallet find(Long id){
        return this.palletRepository.findById(id).orElseThrow(() -> new BadRequestException("Pallet no found"));
    }
}
