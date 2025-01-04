package com.example.library.service;


import com.example.library.repository.LibraryUserRepository;
import com.example.library.model.LibraryUser;
import com.example.library.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final LibraryUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(LibraryUserRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {
        Optional<LibraryUser> userOptional = repository.findByEmail(email);
        if (userOptional.isPresent()) {
            LibraryUser user = userOptional.get();
            if (passwordEncoder.matches(password, user.getHashHasla())) {
                return jwtUtil.generateToken(user.getEmail());
            }
        }
        throw new RuntimeException("Invalid email or password");
    }
}
