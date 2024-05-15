package org.alejo2075.employees_credit.modules.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alejo2075.employees_credit.modules.user.exception.AuthenticationException;
import org.alejo2075.employees_credit.modules.user.jwt.JwtService;
import org.alejo2075.employees_credit.modules.user.model.Role;
import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationResponse;
import org.alejo2075.employees_credit.modules.user.model.entity.User;
import org.alejo2075.employees_credit.modules.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Registers a new user.
     *
     * @param request the authentication request containing user details
     * @return the authentication response containing the JWT token
     */
    @Override
    public AuthenticationResponse register(AuthenticationRequest request) {
        log.info("Attempting to register user with email: {}", request.getEmail());

        // Check if the email is already registered
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            log.error("Email {} is already registered", request.getEmail());
            throw new AuthenticationException("Email is already registered");
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(Role.EMPLOYEE))
                .build();

        var savedUser = repository.save(user);
        log.info("User {} registered successfully", request.getEmail());

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    /**
     * Authenticates a user.
     *
     * @param request the authentication request containing user credentials
     * @return the authentication response containing the JWT token
     */
    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        log.info("Attempting to login user with email: {}", request.getEmail());

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("User with email {} not found", request.getEmail());
                    return new AuthenticationException("User not found");
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.error("Invalid credentials for user {}", request.getEmail());
            throw new AuthenticationException("Invalid credentials");
        }

        var jwtToken = jwtService.generateToken(user);
        log.info("User {} logged in successfully", request.getEmail());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}