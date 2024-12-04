package com.dormhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Nonaktifkan CSRF jika tidak dibutuhkan
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/daftar", "/assets/**").permitAll() // URL bebas akses
                .anyRequest().authenticated() // URL lain memerlukan autentikasi
            )
            .formLogin(login -> login
                .loginPage("/login") // Gunakan halaman login kustom
                .defaultSuccessUrl("/", true) // Redirect setelah login berhasil
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout") // Redirect setelah logout
                .permitAll()
            );
    
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encoder untuk hashing password
    }
}
