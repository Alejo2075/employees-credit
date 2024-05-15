package org.alejo2075.employees_credit.modules.user.exception;

/**
 * Custom exception for authentication-related errors.
 */
public class AuthenticationException extends RuntimeException {

    /**
     * Constructs a new AuthenticationException with the specified detail message.
     *
     * @param message the detail message
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * Constructs a new AuthenticationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}