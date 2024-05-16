package org.alejo2075.employees_credit.modules.credit.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.alejo2075.employees_credit.modules.credit.model.CreditApplicationStatus;
import org.alejo2075.employees_credit.modules.user.model.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity class for credit application.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_applications")
public class CreditApplication {

    /**
     * Unique identifier for the credit application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The user who submitted the application.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The amount requested in the credit application.
     */
    @Column(nullable = false)
    private double amount;

    /**
     * The purpose of the credit application.
     */
    @Column(nullable = false, length = 255)
    private String purpose;

    /**
     * The status of the credit application.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditApplicationStatus status;

    /**
     * The timestamp when the application was submitted.
     */
    @Column(nullable = false)
    private LocalDateTime submittedAt;

    /**
     * The timestamp when the application was reviewed.
     */
    @Column
    private LocalDateTime reviewedAt;

    /**
     * The analyst who reviewed the application.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    /**
     * The notes from the analyst's review.
     */
    @Column(length = 500)
    private String reviewNotes;
}