package org.alejo2075.employees_credit.modules.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alejo2075.employees_credit.modules.user.model.Role;

import java.util.List;

/**
 * DTO for role assignment request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleAssignmentRequest {

    /**
     * Roles to be assigned to the user.
     */
    private List<Role> roles;
}