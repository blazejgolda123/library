package com.example.library.service;
import com.example.library.DTO.*;
import com.example.library.model.Autor;
import com.example.library.model.Book;
import com.example.library.repository.AutorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class BookService {
    private final BookRepository repository;
    private final AutorRepository autorRepository;

    public BookService(BookRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }
    public BookDTO createBook(BookCreateRequest book) {
        Book b = new Book();
        b.setName(book.getName());
        b.setGenre(book.getGenre());
        b.setYear2(book.getYear2());
        b.setDescription(book.getDescription());
        Optional<Autor> a = autorRepository.findById(book.getAutorId());
        if(a.isPresent()){
            b.setAutor(a.get());
        } else {
            //todo zgłoszenie błedu ze nie ma takiego autora
        }

        repository.save(b);

        BookDTO response = new BookDTO();
        response.setId(b.getId());
        response.setName(b.getName());
        response.setGenre(b.getGenre());
        response.setYear2(b.getYear2());
        response.setDescription(b.getDescription());
        if(a.isPresent()){
            AutorDTO autorDTO = new AutorDTO();
            autorDTO.setId(a.get().getId());
            autorDTO.setDataUrodzenia(a.get().getDataUrodzenia());
            autorDTO.setImie(a.get().getImie());
            autorDTO.setNazwisko(a.get().getNazwisko());
            autorDTO.setNarodowosc(a.get().getNarodowosc());
            response.setAutor(autorDTO);
        }
        return response;
    }
    public Optional<BookDTO> findBookById(UUID id) {
        Optional<Book> fromDataBase = repository.findById(id);

        if(fromDataBase.isEmpty()){
            return Optional.empty();
        } else {
            Book b = fromDataBase.get();
            BookDTO response = new BookDTO();
            // response.id = b.id;
            response.setId(b.getId());
            response.setName(b.getName());
            response.setGenre(b.getGenre());
            response.setYear2(b.getYear2());
            response.setDescription(b.getDescription());
            if(b.getAutor() != null){
                AutorDTO autorDTO = new AutorDTO();
                autorDTO.setId(b.getAutor().getId());
                autorDTO.setDataUrodzenia(b.getAutor().getDataUrodzenia());
                autorDTO.setImie(b.getAutor().getImie());
                autorDTO.setNazwisko(b.getAutor().getNazwisko());
                autorDTO.setNarodowosc(b.getAutor().getNarodowosc());
                response.setAutor(autorDTO);
            }
            return Optional.of(response);
        }
    }
    public void deleteBook(UUID id) {
        repository.deleteById(id);
    }
    public BookDTO updateBook(BookUpdateRequest book) {
        Book b = new Book();
        b.setId(book.getId());
        b.setName(book.getName());
        b.setGenre(book.getGenre());
        b.setYear2(book.getYear2());
        b.setDescription(book.getDescription());

        Optional<Autor> a = autorRepository.findById(book.getAutorId());
        if(a.isPresent()){
            b.setAutor(a.get());
        } else {
            //todo zgłoszenie błedu ze nie ma takiego autora
        }

        repository.save(b);

        BookDTO response = new BookDTO();
        response.setId(b.getId());
        response.setName(b.getName());
        response.setGenre(b.getGenre());
        response.setYear2(b.getYear2());
        response.setDescription(b.getDescription());
        if(a.isPresent()){
            AutorDTO autorDTO = new AutorDTO();
            autorDTO.setId(a.get().getId());
            autorDTO.setDataUrodzenia(a.get().getDataUrodzenia());
            autorDTO.setImie(a.get().getImie());
            autorDTO.setNazwisko(a.get().getNazwisko());
            autorDTO.setNarodowosc(a.get().getNarodowosc());
            response.setAutor(autorDTO);
        }
        return response;
    }
}
