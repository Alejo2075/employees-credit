package org.alejo2075.employees_credit.modules.credit.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for credit application request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationRequest {

    /**
     * The amount requested in the credit application.
     */
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    /**
     * The purpose of the credit application.
     */
    @NotBlank(message = "Purpose is required")
    private String purpose;
}