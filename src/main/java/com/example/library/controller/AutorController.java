package com.example.library.controller;
import com.example.library.DTO.AutorCreateRequest;
import com.example.library.DTO.AutorDTO;
import com.example.library.DTO.AutorUpdateRequest;
import com.example.library.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

//todo Doronić zabezpieczenie żeby wszystkie te metody mógł wykonywać tylko admin, zwykły user nie
@RestController
@RequestMapping("/api/autor")
public class AutorController {
    private final AutorService service;
    public AutorController(AutorService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<AutorDTO> create(@RequestBody AutorCreateRequest autor) {
        AutorDTO savedAutor = service.createAutor(autor);
        return ResponseEntity.ok(savedAutor);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> getAutor(@PathVariable UUID id) {
        Optional<AutorDTO> autor = service.findAutorById(id);

        if(autor.isPresent()){
            return ResponseEntity.ok(autor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable UUID id) {
        service.deleteAutor(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> updateAutor(@PathVariable UUID id, @RequestBody AutorUpdateRequest autor) {
        if (!id.equals(autor.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.updateAutor(autor));
    }
}
