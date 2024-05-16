package org.alejo2075.employees_credit.modules.credit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alejo2075.employees_credit.modules.credit.model.CreditApplicationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for credit application response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationResponse {

    /**
     * Unique identifier for the credit application.
     */
    private UUID id;

    /**
     * The amount requested in the credit application.
     */
    private double amount;

    /**
     * The purpose of the credit application.
     */
    private String purpose;

    /**
     * The status of the credit application.
     */
    private CreditApplicationStatus status;

    /**
     * The timestamp when the application was submitted.
     */
    private LocalDateTime submittedAt;

    /**
     * The timestamp when the application was reviewed.
     */
    private LocalDateTime reviewedAt;

    /**
     * The notes from the analyst's review.
     */
    private String reviewNotes;
}