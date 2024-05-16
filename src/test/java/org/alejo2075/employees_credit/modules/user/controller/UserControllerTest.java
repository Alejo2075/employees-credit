package org.alejo2075.employees_credit.modules.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.alejo2075.employees_credit.modules.user.model.dto.ProfileUpdateRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.RoleAssignmentRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.UserResponse;
import org.alejo2075.employees_credit.modules.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser_Success() throws Exception {
        UUID userId = UUID.randomUUID();
        UserResponse userResponse = new UserResponse(userId, "test@example.com", "Test User", "123456", Collections.emptyList());

        when(userService.getUserById(userId)).thenReturn(userResponse);

        mockMvc.perform(get("/api/v1/user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponse)));
    }

    @Test
    void getUser_NotFound() throws Exception {
        UUID userId = UUID.randomUUID();

        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(get("/api/v1/user/{id}", userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUser_Success() throws Exception {
        UUID userId = UUID.randomUUID();
        ProfileUpdateRequest request = new ProfileUpdateRequest("Updated User", "updated@example.com", "newpassword");
        UserResponse userResponse = new UserResponse(userId, "updated@example.com", "Updated User", "123456", Collections.emptyList());

        when(userService.updateUser(eq(userId), any(ProfileUpdateRequest.class))).thenReturn(userResponse);

        mockMvc.perform(put("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponse)));
    }

    @Test
    void updateUser_NotFound() throws Exception {
        UUID userId = UUID.randomUUID();
        ProfileUpdateRequest request = new ProfileUpdateRequest("Updated User", "updated@example.com", "newpassword");

        when(userService.updateUser(eq(userId), any(ProfileUpdateRequest.class))).thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(put("/api/v1/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void assignRole_Success() throws Exception {
        UUID userId = UUID.randomUUID();
        RoleAssignmentRequest request = new RoleAssignmentRequest(Collections.singletonList(Role.ADMIN));
        UserResponse userResponse = new UserResponse(userId, "test@example.com", "Test User", "123456", Collections.singletonList(Role.ADMIN));

        when(userService.assignRole(eq(userId), any(RoleAssignmentRequest.class))).thenReturn(userResponse);

        mockMvc.perform(post("/api/v1/user/{id}/assign-role", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userResponse)));
    }

    @Test
    void assignRole_UserNotFound() throws Exception {
        UUID userId = UUID.randomUUID();
        RoleAssignmentRequest request = new RoleAssignmentRequest(Collections.singletonList(Role.ADMIN));

        when(userService.assignRole(eq(userId), any(RoleAssignmentRequest.class))).thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(post("/api/v1/user/{id}/assign-role", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsers_Success() throws Exception {
        UserResponse userResponse = new UserResponse(UUID.randomUUID(), "test@example.com", "Test User", "123456", Collections.emptyList());

        when(userService.getAllUsers()).thenReturn(Collections.singletonList(userResponse));

        mockMvc.perform(get("/api/v1/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(userResponse))));
    }

    @Test
    void getAllEmployees_Success() throws Exception {
        UserResponse userResponse = new UserResponse(UUID.randomUUID(), "test@example.com", "Test User", "123456", Collections.singletonList(Role.EMPLOYEE));

        when(userService.getAllEmployees()).thenReturn(Collections.singletonList(userResponse));

        mockMvc.perform(get("/api/v1/user/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(userResponse))));
    }

    @Test
    void getAllAnalysts_Success() throws Exception {
        UserResponse userResponse = new UserResponse(UUID.randomUUID(), "test@example.com", "Test User", "123456", Collections.singletonList(Role.ANALYST));

        when(userService.getAllAnalysts()).thenReturn(Collections.singletonList(userResponse));

        mockMvc.perform(get("/api/v1/user/analysts"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(userResponse))));
    }
}