package com.example.projectalllabs.config;

import com.example.projectalllabs.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET).permitAll() // Allow all GET requests
                        .requestMatchers(HttpMethod.PATCH, String.valueOf(HttpMethod.PUT)).authenticated() // Require authentication for PATCH/PUT
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN") // Only allow ADMIN for DELETE
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for simplicity (optional for REST APIs)
                .httpBasic(Customizer.withDefaults())  // Enable HTTP Basic Authentication
                .formLogin(Customizer.withDefaults());  // Optional: Enable Form-based login (if you want to use forms)

        return http.build();  // Finalize the HttpSecurity configuration
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);  // Set custom UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder());  // Set password encoder
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encryption
    }
}
