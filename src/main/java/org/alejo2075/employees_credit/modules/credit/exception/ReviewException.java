package org.alejo2075.employees_credit.modules.credit.exception;

/**
 * Custom exception for review-related errors.
 */
public class ReviewException extends RuntimeException {

    /**
     * Constructs a new ReviewException with the specified detail message.
     *
     * @param message the detail message
     */
    public ReviewException(String message) {
        super(message);
    }

    /**
     * Constructs a new ReviewException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ReviewException(String message, Throwable cause) {
        super(message, cause);
    }
}