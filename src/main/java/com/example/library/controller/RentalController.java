package com.example.library.controller;

import com.example.library.DTO.*;
import com.example.library.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/rental")
class RentalController {
    private final RentalService service;
    public RentalController(RentalService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<RentalDTO> create(@RequestBody RentalCreateRequest rental) {
        return ResponseEntity.ok(service.createRental(rental));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRental(@PathVariable UUID id) {
        Optional<RentalDTO> rental = service.findRentalById(id);
        return rental.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable UUID id) {
        service.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDTO> updateRental(@PathVariable UUID id, @RequestBody RentalUpdateRequest rental) {
        if (!id.equals(rental.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.updateRental(rental));
    }
}
