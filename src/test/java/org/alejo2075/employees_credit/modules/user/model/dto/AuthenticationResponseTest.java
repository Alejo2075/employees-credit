package org.alejo2075.employees_credit.modules.user.model.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationResponseTest {

    @Test
    void testAuthenticationResponse() {
        String token = "test-token";
        AuthenticationResponse response = new AuthenticationResponse(token);

        assertThat(response).isNotNull();
        assertThat(response.getAccessToken()).isEqualTo(token);
    }

    @Test
    void testBuilder() {
        String token = "test-token";
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken(token)
                .build();

        assertThat(response).isNotNull();
        assertThat(response.getAccessToken()).isEqualTo(token);
    }
}