package com.dormhub.config;

import com.dormhub.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class); // Tambahkan logger

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Nonaktifkan CSRF jika tidak dibutuhkan
            .headers(headers -> headers.frameOptions().sameOrigin()) // Untuk iframe jika diperlukan
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/daftar", "/assets/**", "/css/**", "/js/**", "/img/**").permitAll() // URL bebas akses
                .anyRequest().authenticated() // URL lain memerlukan autentikasi
            )
            .formLogin(login -> login
                .loginPage("/login") // Gunakan halaman login kustom
                .loginProcessingUrl("/login") // URL untuk memproses login
                .usernameParameter("email") // Parameter untuk username diubah menjadi email
                .passwordParameter("password") // Parameter untuk password tetap
                .defaultSuccessUrl("/", true) // Redirect default setelah login berhasil
                .successHandler((request, response, authentication) -> {
                    // Redirect berdasarkan role
                    String redirectUrl = "/";
                    if (authentication.getAuthorities().stream()
                            .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
                        redirectUrl = "/admin/dashboard";
                    } else if (authentication.getAuthorities().stream()
                            .anyMatch(role -> role.getAuthority().equals("ROLE_MAHASISWA"))) {
                        redirectUrl = "/mahasiswa/dashboard";
                    } else if (authentication.getAuthorities().stream()
                            .anyMatch(role -> role.getAuthority().equals("ROLE_HELPDESK"))) {
                        redirectUrl = "/help-desk/dashboard";
                    }
                    response.sendRedirect(redirectUrl);
                })
                .failureUrl("/login?error=true") // Redirect jika login gagal
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL untuk logout
                .logoutSuccessUrl("/login?logout") // Redirect setelah logout
                .permitAll()
            )
            .rememberMe(remember -> remember
                .key("uniqueAndSecretKey") // Key aman untuk fitur "Ingat Saya"
                .tokenValiditySeconds(1209600) // Durasi 2 minggu
            );

        logger.info("SecurityFilterChain konfigurasi selesai"); // Gunakan logger di sini
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encoder untuk hashing password
    }
}
