package com.tecmarch.tecmarch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Recursos estáticos
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/fonts/**", "/webjars/**").permitAll()

                        // Páginas públicas (ajusta según tus rutas reales)
                        .requestMatchers("/", "/home", "/productos", "/productos/**",
                                "/contacto", "/contacto/**",
                                "/serviciotecnico", "/serviciotecnico/**").permitAll()

                        // Login
                        .requestMatchers("/login", "/login/**").permitAll()

                        // Página de error (importante para evitar loops en algunos casos)
                        .requestMatchers("/error").permitAll()

                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)  // Cambia si tu dashboard tiene otro nombre
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}