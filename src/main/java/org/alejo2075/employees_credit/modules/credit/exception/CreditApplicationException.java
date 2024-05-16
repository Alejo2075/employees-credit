package org.alejo2075.employees_credit.modules.credit.exception;

/**
 * Custom exception for credit application-related errors.
 */
public class CreditApplicationException extends RuntimeException {

    /**
     * Constructs a new CreditApplicationException with the specified detail message.
     *
     * @param message the detail message
     */
    public CreditApplicationException(String message) {
        super(message);
    }

    /**
     * Constructs a new CreditApplicationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public CreditApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}