package org.alejo2075.employees_credit.modules.credit.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alejo2075.employees_credit.modules.credit.model.CreditApplicationStatus;

import java.util.UUID;

/**
 * DTO for credit application review request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationReviewRequest {

    /**
     * The status of the credit application after review.
     */
    @NotNull(message = "Status is required")
    private CreditApplicationStatus status;

    /**
     * The notes from the analyst's review.
     */
    @NotBlank(message = "Review notes are required")
    private String reviewNotes;
}