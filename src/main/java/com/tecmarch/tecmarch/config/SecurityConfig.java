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

                        // Páginas públicas
                        .requestMatchers("/", "/home", "/productos", "/productos/**",
                                "/contacto", "/contacto/**",
                                "/serviciotecnico", "/serviciotecnico/**").permitAll()

                        // Login y logout
                        .requestMatchers("/login", "/login/**", "/logout").permitAll()

                        // Página de error
                        .requestMatchers("/error").permitAll()

                        // Panel administrativo: SOLO usuarios con rol ADMIN
                        .requestMatchers("/admin/**", "/admin/dashboard").hasRole("ADMIN")

                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/admin/dashboard", true)  // ← Va directo al dashboard admin
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