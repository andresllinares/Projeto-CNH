package com.fiap.juarez.cnh.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private ValidateToken validateToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(
            HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/usuarios").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/usuarios/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/api/usuarios/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/usuarios/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/carros").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/carros/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/api/carros").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/api/carros/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/carros/*").hasRole("ADMIN")
                            .anyRequest().authenticated()
                )
                .addFilterBefore(validateToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() {

        };
    }
}