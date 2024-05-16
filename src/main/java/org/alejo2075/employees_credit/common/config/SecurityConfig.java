package org.alejo2075.employees_credit.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.alejo2075.employees_credit.common.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration class for security settings.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@Log4j2
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * Configures the security filter chain.
     *
     * @param http the HTTP security configuration
     * @return the security filter chain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configuring security filter chain");

        http
                // Disable CSRF protection as it is not needed for JWT-based authentication
                .csrf(AbstractHttpConfigurer::disable)

                // Configure request authorization
                .authorizeHttpRequests(req ->
                        req
                                // Permit all requests to the login and register endpoints
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                // Require authentication for all other requests
                                .requestMatchers("/api/v1/user/**").authenticated()
                                .anyRequest().authenticated()
                )

                // Set session management to stateless as we are using JWTs
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))

                // Add the JWT authentication filter before the username-password authentication filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        log.info("Security filter chain configured successfully");

        return http.build();
    }
}