package org.alejo2075.employees_credit.common.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationConfigTest {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void authenticationManagerBeanShouldBeConfigured() {
        assertThat(authenticationManager).isNotNull();
    }

    @Test
    void passwordEncoderBeanShouldBeConfigured() {
        assertThat(passwordEncoder).isNotNull();
        assertThat(passwordEncoder.encode("password")).isNotNull();
    }
}