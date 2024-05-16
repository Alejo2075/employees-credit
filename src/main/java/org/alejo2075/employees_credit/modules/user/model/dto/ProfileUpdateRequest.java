package org.alejo2075.employees_credit.modules.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for profile update request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateRequest {

    /**
     * Full name of the user.
     */
    @NotBlank(message = "Full name cannot be blank")
    @Size(max = 100, message = "Full name must be less than 100 characters")
    private String fullName;

    /**
     * Email of the user.
     */
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    /**
     * Password of the user.
     */
    @Size(min = 8, max = 25, message = "Password must be between 8 and 25 characters")
    private String password;
}