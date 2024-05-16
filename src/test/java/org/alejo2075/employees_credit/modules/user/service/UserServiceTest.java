package org.alejo2075.employees_credit.modules.user.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.alejo2075.employees_credit.common.email.EmailService;
import org.alejo2075.employees_credit.modules.user.exception.UserNotFoundException;
import org.alejo2075.employees_credit.modules.user.model.Role;
import org.alejo2075.employees_credit.modules.user.model.dto.ProfileUpdateRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.RoleAssignmentRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.UserResponse;
import org.alejo2075.employees_credit.modules.user.model.entity.User;
import org.alejo2075.employees_credit.modules.user.repository.UserRepository;
import org.alejo2075.employees_credit.modules.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_Success() {
        UUID userId = UUID.randomUUID();
        User user = User.builder().id(userId).email("test@example.com").fullName("Test User").build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserResponse userResponse = userService.getUserById(userId);

        assertNotNull(userResponse);
        assertEquals("test@example.com", userResponse.getEmail());
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetUserById_UserNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
        verify(userRepository).findById(userId);
    }

    @Test
    void testUpdateUser_Success() {
        UUID userId = UUID.randomUUID();
        User user = User.builder().id(userId).email("test@example.com").fullName("Test User").build();
        ProfileUpdateRequest request = new ProfileUpdateRequest("Updated User", "updated@example.com", "newpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse userResponse = userService.updateUser(userId, request);

        assertNotNull(userResponse);
        assertEquals("updated@example.com", userResponse.getEmail());
        assertEquals("Updated User", userResponse.getFullName());
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(User.class));
        verify(emailService).sendEmail(eq("updated@example.com"), anyString(), anyString());
    }

    @Test
    void testUpdateUser_UserNotFound() {
        UUID userId = UUID.randomUUID();
        ProfileUpdateRequest request = new ProfileUpdateRequest("Updated User", "updated@example.com", "newpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, request));
        verify(userRepository).findById(userId);
    }

    @Test
    void testAssignRole_Success() {
        UUID userId = UUID.randomUUID();
        User user = User.builder().id(userId).email("test@example.com").roles(Collections.emptyList()).build();
        RoleAssignmentRequest request = new RoleAssignmentRequest(Collections.singletonList(Role.ADMIN));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse userResponse = userService.assignRole(userId, request);

        assertNotNull(userResponse);
        assertTrue(userResponse.getRoles().contains(Role.ADMIN));
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(User.class));
        verify(emailService).sendEmail(eq("test@example.com"), anyString(), anyString());
    }

    @Test
    void testAssignRole_UserNotFound() {
        UUID userId = UUID.randomUUID();
        RoleAssignmentRequest request = new RoleAssignmentRequest(Collections.singletonList(Role.ADMIN));

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.assignRole(userId, request));
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetAllUsers_Success() {
        User user = User.builder().id(UUID.randomUUID()).email("test@example.com").fullName("Test User").build();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UserResponse> users = userService.getAllUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    void testGetAllEmployees_Success() {
        User user = User.builder().id(UUID.randomUUID()).email("test@example.com").fullName("Test User").roles(Collections.singletonList(Role.EMPLOYEE)).build();
        when(userRepository.findByRolesContaining(Role.EMPLOYEE)).thenReturn(Collections.singletonList(user));

        List<UserResponse> users = userService.getAllEmployees();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userRepository).findByRolesContaining(Role.EMPLOYEE);
    }

    @Test
    void testGetAllAnalysts_Success() {
        User user = User.builder().id(UUID.randomUUID()).email("test@example.com").fullName("Test User").roles(Collections.singletonList(Role.ANALYST)).build();
        when(userRepository.findByRolesContaining(Role.ANALYST)).thenReturn(Collections.singletonList(user));

        List<UserResponse> users = userService.getAllAnalysts();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userRepository).findByRolesContaining(Role.ANALYST);
    }
}