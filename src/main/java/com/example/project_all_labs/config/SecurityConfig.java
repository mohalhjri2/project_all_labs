package com.example.project_all_labs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/graphql").permitAll() // Allow GraphQL for all
                        .requestMatchers("/pets/**", "/households/**").authenticated() // Require authentication
                        .requestMatchers(HttpMethod.PATCH, String.valueOf(HttpMethod.PUT)).hasAnyRole("USER", "ADMIN") // Edit pets/households
                        .anyRequest().hasRole("ADMIN") // Admin-only access for other requests
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF using the new API
                .httpBasic(Customizer.withDefaults()); // Enable Basic Authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use no-operation password encoder (accept plaintext passwords)
        return NoOpPasswordEncoder.getInstance();
    }
}
