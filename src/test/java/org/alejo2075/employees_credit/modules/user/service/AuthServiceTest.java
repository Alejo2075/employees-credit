package org.alejo2075.employees_credit.modules.user.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.alejo2075.employees_credit.common.email.EmailService;
import org.alejo2075.employees_credit.common.jwt.JwtService;
import org.alejo2075.employees_credit.modules.user.exception.AuthenticationException;
import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationResponse;
import org.alejo2075.employees_credit.modules.user.model.entity.User;
import org.alejo2075.employees_credit.modules.user.repository.UserRepository;
import org.alejo2075.employees_credit.modules.user.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_Success() {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        User user = User.builder().email(request.getEmail()).password("encodedPassword").build();
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwtToken");

        AuthenticationResponse response = authService.register(request);

        assertEquals("jwtToken", response.getAccessToken());
        verify(userRepository).save(any(User.class));
        verify(emailService).sendEmail(eq(request.getEmail()), anyString(), anyString());
    }

    @Test
    void testRegister_EmailAlreadyRegistered() {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(AuthenticationException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLogin_Success() {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        User user = User.builder().email(request.getEmail()).password("encodedPassword").build();
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("jwtToken");

        AuthenticationResponse response = authService.login(request);

        assertEquals("jwtToken", response.getAccessToken());
    }

    @Test
    void testLogin_UserNotFound() {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        assertThrows(AuthenticationException.class, () -> authService.login(request));
    }

    @Test
    void testLogin_InvalidCredentials() {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        User user = User.builder().email(request.getEmail()).password("encodedPassword").build();
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(AuthenticationException.class, () -> authService.login(request));
    }
}