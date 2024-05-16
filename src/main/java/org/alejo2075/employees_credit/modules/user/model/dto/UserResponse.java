package org.alejo2075.employees_credit.modules.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alejo2075.employees_credit.modules.user.model.Role;

import java.util.List;
import java.util.UUID;

/**
 * DTO for user response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    /**
     * Unique identifier for the user.
     */
    private UUID id;

    /**
     * Email of the user.
     */
    private String email;

    /**
     * Full name of the user.
     */
    private String fullName;

    /**
     * Government ID of the user.
     */
    private String govId;

    /**
     * Roles assigned to the user.
     */
    private List<Role> roles;
}