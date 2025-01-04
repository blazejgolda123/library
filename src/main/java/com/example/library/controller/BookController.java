package com.example.library.controller;
import com.example.library.DTO.*;
import com.example.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

//todo Doronić zabezpieczenie żeby wszystkie te metody mógł wykonywać tylko admin, zwykły user nie
@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService service;
    public BookController(BookService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public ResponseEntity<BookDTO> create(@RequestBody BookCreateRequest book) {
        return ResponseEntity.ok(service.createBook(book));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable UUID id) {
        Optional<BookDTO> book = service.findBookById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable UUID id, @RequestBody BookUpdateRequest book) {
        if (!id.equals(book.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.updateBook(book));
    }
}
