package org.alejo2075.employees_credit.modules.user.service;

import lombok.RequiredArgsConstructor;
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
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(AuthenticationRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(Role.EMPLOYEE))
                .build();

        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid credentials");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
