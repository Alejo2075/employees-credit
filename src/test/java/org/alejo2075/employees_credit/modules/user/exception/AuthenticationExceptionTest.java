package org.alejo2075.employees_credit.modules.user.exception;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuthenticationExceptionTest {

    @Test
    void testAuthenticationExceptionWithMessage() {
        String errorMessage = "Test error message";
        AuthenticationException exception = new AuthenticationException(errorMessage);

        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void testAuthenticationExceptionWithMessageAndCause() {
        String errorMessage = "Test error message";
        Throwable cause = new RuntimeException("Cause of the error");
        AuthenticationException exception = new AuthenticationException(errorMessage, cause);

        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testAuthenticationExceptionThrown() {
        assertThatThrownBy(() -> {
            throw new AuthenticationException("Error occurred");
        }).isInstanceOf(AuthenticationException.class)
                .hasMessage("Error occurred");
    }
}