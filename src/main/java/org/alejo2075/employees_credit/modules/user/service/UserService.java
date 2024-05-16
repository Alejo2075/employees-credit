package org.alejo2075.employees_credit.modules.user.service;

import org.alejo2075.employees_credit.modules.user.model.dto.ProfileUpdateRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.RoleAssignmentRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.UserResponse;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for user-related operations.
 */
public interface UserService {

    /**
     * Gets user information by ID.
     *
     * @param id the user ID
     * @return the user information
     */
    UserResponse getUserById(UUID id);

    /**
     * Updates user information by ID.
     *
     * @param id the user ID
     * @param request the profile update request containing updated user details
     * @return the updated user information
     */
    UserResponse updateUser(UUID id, ProfileUpdateRequest request);

    /**
     * Assigns roles to a user.
     *
     * @param id the user ID
     * @param request the role assignment request containing the new roles
     * @return the updated user information
     */
    UserResponse assignRole(UUID id, RoleAssignmentRequest request);

    /**
     * Gets all users.
     *
     * @return the list of all users
     */
    List<UserResponse> getAllUsers();

    /**
     * Gets all employees.
     *
     * @return the list of all employees
     */
    List<UserResponse> getAllEmployees();

    /**
     * Gets all analysts.
     *
     * @return the list of all analysts
     */
    List<UserResponse> getAllAnalysts();
}