package com.update.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // ✅ Disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll() // ✅ Allow all endpoints
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults()); // Optional: enable basic auth (for test)

        return http.build();
    }
}
