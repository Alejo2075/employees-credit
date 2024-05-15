package org.alejo2075.employees_credit.modules.user.model.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenEmailIsNull_shouldHaveConstraintViolations() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email(null)
                .password("password123")
                .build();

        Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);

        assertThat(violations).hasSize(1);
    }

    @Test
    void whenPasswordIsTooShort_shouldHaveConstraintViolations() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("user@example.com")
                .password("short")
                .build();

        Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);

        assertThat(violations).hasSize(1);
    }

    @Test
    void whenValidRequest_shouldHaveNoConstraintViolations() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("user@example.com")
                .password("password123")
                .build();

        Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }
}