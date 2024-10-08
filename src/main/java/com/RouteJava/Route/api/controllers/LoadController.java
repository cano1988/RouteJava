package com.RouteJava.Route.api.controllers;


import com.RouteJava.Route.api.dto.request.LoadRequest;
import com.RouteJava.Route.api.dto.request.PalletRequest;
import com.RouteJava.Route.api.dto.response.LoadResp;
import com.RouteJava.Route.api.dto.response.PalletResp;
import com.RouteJava.Route.infraestructure.abstract_services.IPalletService;
import com.RouteJava.Route.infraestructure.services.LoadService;
import com.RouteJava.Route.util.enums.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/loads")
@RequiredArgsConstructor
public class LoadController {

    @Autowired
    private final LoadService loadService;

    @GetMapping
    public ResponseEntity<Page<LoadResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.loadService.getAll(page - 1, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoadResp> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loadService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LoadResp> create (  @RequestBody LoadRequest request) {
        return ResponseEntity.ok(this.loadService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoadResp> update(@PathVariable Long id, @RequestBody LoadRequest request) {
        return ResponseEntity.ok(this.loadService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.loadService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
