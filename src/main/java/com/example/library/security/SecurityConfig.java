package com.example.library.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//"/api/libraryUser/login", "/api/libraryUser/register"

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Wyłączenie CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/libraryUser/login", "/api/libraryUser/register", "/h2-console/**").permitAll() // Endpoint logowania dostępny dla wszystkich
                        .anyRequest().authenticated() // Wszystkie inne wymagają autoryzacji
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Bezstanowe sesje
                ).headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable()) // Zezwolenie na iframe (potrzebne dla H2 Console)
                );

        // Dodanie filtra JWT
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


