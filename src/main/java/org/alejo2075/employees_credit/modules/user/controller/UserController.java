package org.alejo2075.employees_credit.modules.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.alejo2075.employees_credit.modules.user.model.dto.ProfileUpdateRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.RoleAssignmentRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.UserResponse;
import org.alejo2075.employees_credit.modules.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for user-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    /**
     * Gets user information by ID.
     *
     * @param id the user ID
     * @return the user information
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user information", description = "Retrieve information of a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        log.info("Retrieving user information for user with ID: {}", id);
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Updates user information by ID.
     *
     * @param id the user ID
     * @param request the profile update request containing updated user details
     * @return the updated user information
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update user information", description = "Update information of a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody ProfileUpdateRequest request) {
        log.info("Updating user information for user with ID: {}", id);
        UserResponse updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Assigns roles to a user (admin only).
     *
     * @param id the user ID
     * @param request the role assignment request containing the new roles
     * @return the updated user information
     */
    @PostMapping("/{id}/assign-role")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Assign roles to a user", description = "Assign roles to a user (admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful role assignment", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResponse> assignRole(@PathVariable UUID id, @RequestBody RoleAssignmentRequest request) {
        log.info("Assigning roles to user with ID: {}", id);
        UserResponse updatedUser = userService.assignRole(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Gets all users (admin only).
     *
     * @return the list of all users
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users", description = "Retrieve a list of all users (admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    })
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Retrieving all users");
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Gets all employees (admin and analyst only).
     *
     * @return the list of all employees
     */
    @GetMapping("/employees")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees (admin and analyst only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    })
    public ResponseEntity<List<UserResponse>> getAllEmployees() {
        log.info("Retrieving all employees");
        List<UserResponse> employees = userService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Gets all analysts (admin only).
     *
     *
     * @return the list of all analysts
     */
    @GetMapping("/analysts")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all analysts", description = "Retrieve a list of all analysts (admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    })
    public ResponseEntity<List<UserResponse>> getAllAnalysts() {
        log.info("Retrieving all analysts");
        List<UserResponse> analysts = userService.getAllAnalysts();
        return ResponseEntity.ok(analysts);
    }
}