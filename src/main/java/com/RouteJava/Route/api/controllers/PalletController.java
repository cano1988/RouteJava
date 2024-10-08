package com.RouteJava.Route.api.controllers;

import com.RouteJava.Route.api.dto.request.PalletRequest;
import com.RouteJava.Route.api.dto.response.PalletResp;
import com.RouteJava.Route.infraestructure.abstract_services.IPalletService;
import com.RouteJava.Route.util.enums.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/pallets")
@RequiredArgsConstructor
public class PalletController {

    @Autowired
    private final IPalletService palletService;

    @GetMapping
    public ResponseEntity<Page<PalletResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.palletService.getAll(page - 1, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalletResp> getById(@PathVariable Long id) {
        return ResponseEntity.ok(palletService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PalletResp> create (  @RequestBody PalletRequest request) {
        return ResponseEntity.ok(this.palletService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PalletResp> update(@PathVariable Long id, @RequestBody PalletRequest request) {
        return ResponseEntity.ok(this.palletService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.palletService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

