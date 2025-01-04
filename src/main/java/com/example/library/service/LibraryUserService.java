package com.example.library.service;
import com.example.library.DTO.*;
import com.example.library.model.LibraryUser;
import com.example.library.repository.LibraryUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryUserService {
    private final LibraryUserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LibraryUserService(LibraryUserRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public LibraryUserDTO createLibraryUser(LibraryUserCreateRequest libraryUserCreateRequest) {
        //todo emaila itp

        LibraryUser lu = new LibraryUser();
        lu.setFirstName(libraryUserCreateRequest.getFirstName());
        lu.setLastName(libraryUserCreateRequest.getLastName());
        lu.setEmail(libraryUserCreateRequest.getEmail());
        lu.setAdmin(libraryUserCreateRequest.getAdmin());
        lu.setHashHasla(passwordEncoder.encode(libraryUserCreateRequest.getHaslo()));

        repository.save(lu);

        LibraryUserDTO response = new LibraryUserDTO();
        response.setId(lu.getId());
        response.setFirstName(lu.getFirstName());
        response.setLastName(lu.getLastName());
        response.setEmail(lu.getEmail());
        response.setAdmin(lu.getAdmin());
        return response;
    }

    public Optional<LibraryUserDTO> findLibraryUserById(UUID id) {
        Optional<LibraryUser> fromDataBase = repository.findById(id);

        if(fromDataBase.isEmpty()){
            return Optional.empty();
        } else {
            LibraryUserDTO response = new LibraryUserDTO();
            response.setId(fromDataBase.get().getId());
            response.setFirstName(fromDataBase.get().getFirstName());
            response.setLastName(fromDataBase.get().getLastName());
            response.setEmail(fromDataBase.get().getEmail());
            response.setAdmin(fromDataBase.get().getAdmin());
            return Optional.of(response);
        }
    }

    public void deleteLibraryUser(UUID id) {
        repository.deleteById(id);
    }

    public boolean authenticate(String email, String password) {
        Optional<LibraryUser> user = repository.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getHashHasla());
    }
    public LibraryUserDTO updateLibraryUser(LibraryUserUpdateRequest user) {
        //todo emaila itp

        LibraryUser lu = new LibraryUser();
        lu.setId(user.getId());
        lu.setFirstName(user.getFirstName());
        lu.setLastName(user.getLastName());
        lu.setEmail(user.getEmail());
        lu.setAdmin(user.getAdmin());
        lu.setHashHasla(passwordEncoder.encode(user.getHaslo()));
        repository.save(lu);
        LibraryUserDTO response = new LibraryUserDTO();
        response.setId(lu.getId());
        response.setFirstName(lu.getFirstName());
        response.setLastName(lu.getLastName());
        response.setEmail(lu.getEmail());
        response.setAdmin(lu.getAdmin());
        return response;
    }

}
