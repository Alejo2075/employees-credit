package org.alejo2075.employees_credit.modules.user.jwt;

import io.jsonwebtoken.Claims;
import org.alejo2075.employees_credit.modules.user.model.Role;
import org.alejo2075.employees_credit.modules.user.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private JwtService mockJwtService;

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration}")
    private long jwtExpiration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtService.setSecretKey(secretKey);  // Set secretKey
        jwtService.setJwtExpiration(jwtExpiration);  // Set jwtExpiration
    }

    @Test
    void testExtractUsername() {
        String token = jwtService.generateToken(createTestUser());
        String username = jwtService.extractUsername(token);

        assertThat(username).isEqualTo("user@example.com");
    }

    @Test
    void testExtractRoles() {
        String token = jwtService.generateToken(createTestUser());
        List<String> roles = jwtService.extractRoles(token);

        assertThat(roles).contains("EMPLOYEE");
    }

    @Test
    void testGenerateToken() {
        User user = createTestUser();
        String token = jwtService.generateToken(user);

        assertThat(token).isNotNull();
    }

    @Test
    void testIsTokenValid() {
        String token = jwtService.generateToken(createTestUser());
        boolean isValid = jwtService.isTokenValid(token);

        assertThat(isValid).isTrue();
    }

    @Test
    void testIsTokenExpired() {
        String token = jwtService.generateToken(createTestUser());
        boolean isExpired = jwtService.isTokenExpired(token);

        assertThat(isExpired).isFalse();
    }

    @Test
    void testExtractExpiration() {
        String token = jwtService.generateToken(createTestUser());
        Date expiration = jwtService.extractExpiration(token);

        assertThat(expiration).isAfter(new Date());
    }

    @Test
    void testExtractAllClaims() {
        String token = jwtService.generateToken(createTestUser());
        Claims claims = jwtService.extractAllClaims(token);

        assertThat(claims.getSubject()).isEqualTo("user@example.com");
    }

    private User createTestUser() {
        return User.builder()
                .email("user@example.com")
                .password("password123")
                .roles(List.of(Role.EMPLOYEE))
                .build();
    }
}