package org.alejo2075.employees_credit.modules.credit.repository;

import org.alejo2075.employees_credit.modules.credit.model.entity.CreditApplication;
import org.alejo2075.employees_credit.modules.credit.model.CreditApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for CreditApplication entity.
 */
@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, UUID> {

    /**
     * Finds all credit applications by status.
     *
     * @param status the status of the credit applications
     * @return a list of credit applications with the given status
     */
    List<CreditApplication> findByStatus(CreditApplicationStatus status);
}