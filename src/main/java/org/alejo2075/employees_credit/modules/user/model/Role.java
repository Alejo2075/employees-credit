package org.alejo2075.employees_credit.modules.user.model;

/**
 * Enumeration for user roles in the application.
 */
public enum Role {
    /**
     * Role for administrators with full access to all features.
     */
    ADMIN,

    /**
     * Role for analysts with access to specific features related to data analysis.
     */
    ANALYST,

    /**
     * Role for regular employees with access to standard features.
     */
    EMPLOYEE
}