package com.RouteJava.Route.api.controllers;


import com.RouteJava.Route.api.dto.request.CarrierRequest;
import com.RouteJava.Route.api.dto.request.LoadRequest;
import com.RouteJava.Route.api.dto.response.CarrierResp;
import com.RouteJava.Route.api.dto.response.LoadResp;
import com.RouteJava.Route.infraestructure.services.CarrierService;
import com.RouteJava.Route.infraestructure.services.LoadService;
import com.RouteJava.Route.util.enums.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/carriers")
@RequiredArgsConstructor
public class CarrierController {

    @Autowired
    private final CarrierService carrierService;

    @GetMapping
    public ResponseEntity<Page<CarrierResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.carrierService.getAll(page - 1, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrierResp> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carrierService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CarrierResp> create (  @RequestBody CarrierRequest request) {
        return ResponseEntity.ok(this.carrierService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrierResp> update(@PathVariable Long id, @RequestBody CarrierRequest request) {
        return ResponseEntity.ok(this.carrierService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.carrierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
