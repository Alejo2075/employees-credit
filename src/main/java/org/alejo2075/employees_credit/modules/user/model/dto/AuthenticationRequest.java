package org.alejo2075.employees_credit.modules.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for authentication request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    /**
     * Email of the user.
     */
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    /**
     * Password of the user.
     */
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 25, message = "Password must be between 8 and 25 characters")
    private String password;
}