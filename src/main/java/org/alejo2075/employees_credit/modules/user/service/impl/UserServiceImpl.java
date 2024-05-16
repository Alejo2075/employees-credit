package org.alejo2075.employees_credit.modules.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.alejo2075.employees_credit.modules.user.exception.UserNotFoundException;
import org.alejo2075.employees_credit.modules.user.model.Role;
import org.alejo2075.employees_credit.modules.user.model.dto.ProfileUpdateRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.RoleAssignmentRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.UserResponse;
import org.alejo2075.employees_credit.modules.user.model.entity.User;
import org.alejo2075.employees_credit.modules.user.repository.UserRepository;
import org.alejo2075.employees_credit.modules.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service implementation for user-related operations.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Gets user information by ID.
     *
     * @param id the user ID
     * @return the user information
     */
    @Override
    public UserResponse getUserById(UUID id) {
        log.info("Fetching user with ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return convertToUserResponse(user);
    }

    /**
     * Updates user information by ID.
     *
     * @param id the user ID
     * @param request the profile update request containing updated user details
     * @return the updated user information
     */
    @Override
    public UserResponse updateUser(UUID id, ProfileUpdateRequest request) {
        log.info("Updating user with ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        log.info("User with ID: {} updated successfully", id);
        return convertToUserResponse(updatedUser);
    }

    /**
     * Assigns roles to a user.
     *
     * @param id the user ID
     * @param request the role assignment request containing the new roles
     * @return the updated user information
     */
    @Override
    public UserResponse assignRole(UUID id, RoleAssignmentRequest request) {
        log.info("Assigning roles to user with ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        List<Role> currentRoles = user.getRoles();
        List<Role> newRoles = request.getRoles();

        for (Role role : newRoles) {
            if (!currentRoles.contains(role)) {
                currentRoles.add(role);
            }
        }

        user.setRoles(currentRoles);

        User updatedUser = userRepository.save(user);
        log.info("Roles assigned to user with ID: {} successfully", id);

        // Send an email notification about the role assignment
        emailService.sendEmail(
                user.getEmail(),
                "Roles Updated",
                "Your roles have been updated successfully."
        );

        return convertToUserResponse(updatedUser);
    }

    /**
     * Gets all users.
     *
     * @return the list of all users
     */
    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * Gets all employees.
     *
     * @return the list of all employees
     */
    @Override
    public List<UserResponse> getAllEmployees() {
        log.info("Fetching all employees");
        return userRepository.findByRolesContaining(Role.EMPLOYEE).stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * Gets all analysts.
     *
     * @return the list of all analysts
     */
    @Override
    public List<UserResponse> getAllAnalysts() {
        log.info("Fetching all analysts");
        return userRepository.findByRolesContaining(Role.ANALYST).stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * Converts a User entity to a UserResponseDTO.
     *
     * @param user the user entity
     * @return the user response DTO
     */
    private UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .govId(user.getGovId())
                .roles(user.getRoles())
                .build();
    }
}