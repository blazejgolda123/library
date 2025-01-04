package com.example.library.controller;

import com.example.library.DTO.*;
import com.example.library.service.AuthService;
import com.example.library.service.LibraryUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

//todo Doronić zabezpieczenie żeby wszystkie te metody mógł wykonywać tylko admin, zwykły user nie (z wyłączeniem register oraz login)
@RestController
@RequestMapping("/api/libraryUser")
public class LibraryUserController {
    private final LibraryUserService service;
    private final AuthService authService;

    public LibraryUserController(LibraryUserService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<LibraryUserDTO> register(@RequestBody LibraryUserCreateRequest libraryUserCreateRequest) {
        LibraryUserDTO savedLibraryUser = service.createLibraryUser(libraryUserCreateRequest);
        return ResponseEntity.ok(savedLibraryUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryUserDTO> getUser(@PathVariable UUID id) {
        Optional<LibraryUserDTO> libraryUser = service.findLibraryUserById(id);
        return libraryUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryUserDTO> updateUser(@PathVariable UUID id, @RequestBody LibraryUserUpdateRequest user) {
        if (!id.equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.updateLibraryUser(user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        service.deleteLibraryUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}