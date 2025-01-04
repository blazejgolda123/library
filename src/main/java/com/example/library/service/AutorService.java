package com.example.library.service;
import com.example.library.DTO.AutorCreateRequest;
import com.example.library.DTO.AutorDTO;
import com.example.library.DTO.AutorUpdateRequest;
import com.example.library.model.Autor;
import com.example.library.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;


    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public AutorDTO createAutor(AutorCreateRequest autor) {
        Autor a = new Autor();
        a.setDataUrodzenia(autor.getDataUrodzenia());
        a.setImie(autor.getImie());
        a.setNazwisko(autor.getNazwisko());
        a.setNarodowosc(autor.getNarodowosc());

        repository.save(a);

        AutorDTO response = new AutorDTO();
        response.setId(a.getId());
        response.setDataUrodzenia(a.getDataUrodzenia());
        response.setImie(a.getImie());
        response.setNazwisko(a.getNazwisko());
        response.setNarodowosc(a.getNarodowosc());
        return response;
    }
    public Optional<AutorDTO> findAutorById(UUID id) {
        Optional<Autor> fromDataBase = repository.findById(id);

        if(fromDataBase.isEmpty()){
            return Optional.empty();
        } else {
            Autor a = fromDataBase.get();
            AutorDTO response = new AutorDTO();
            response.setId(a.getId());
            response.setDataUrodzenia(a.getDataUrodzenia());
            response.setImie(a.getImie());
            response.setNazwisko(a.getNazwisko());
            response.setNarodowosc(a.getNarodowosc());
            return Optional.of(response);
        }
    }
    public void deleteAutor(UUID id) {
        repository.deleteById(id);
    }
    public AutorDTO updateAutor(AutorUpdateRequest autor) {
        Autor a = new Autor();
        a.setId(autor.getId());
        a.setDataUrodzenia(autor.getDataUrodzenia());
        a.setImie(autor.getImie());
        a.setNazwisko(autor.getNazwisko());
        a.setNarodowosc(autor.getNarodowosc());

        repository.save(a);

        AutorDTO response = new AutorDTO();
        response.setId(a.getId());
        response.setDataUrodzenia(a.getDataUrodzenia());
        response.setImie(a.getImie());
        response.setNazwisko(a.getNazwisko());
        response.setNarodowosc(a.getNarodowosc());
        return response;
    }
}
