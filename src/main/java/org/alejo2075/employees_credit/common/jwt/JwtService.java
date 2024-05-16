package org.alejo2075.employees_credit.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.alejo2075.employees_credit.modules.user.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JWT operations.
 */
@Service
@Getter
@Setter
@Log4j2
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration}")
    private long jwtExpiration;

    /**
     * Extracts the username from the given JWT token.
     *
     * @param token the JWT token
     * @return the username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the roles from the given JWT token.
     *
     * @param token the JWT token
     * @return the list of roles
     */
    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }

    /**
     * Extracts a specific claim from the given JWT token.
     *
     * @param token the JWT token
     * @param claimsResolver the function to resolve the claim
     * @param <T> the type of the claim
     * @return the claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT token for the given user.
     *
     * @param user the user
     * @return the JWT token
     */
    public String generateToken(User user) {
        return generateToken(Map.of("roles", user.getRoles()), user);
    }

    /**
     * Generates a JWT token with additional claims for the given user.
     *
     * @param extraClaims the additional claims
     * @param user the user
     * @return the JWT token
     */
    public String generateToken(Map<String, Object> extraClaims, User user) {
        return buildToken(extraClaims, user.getEmail(), jwtExpiration);
    }

    /**
     * Builds a JWT token with the given claims, subject, and expiration time.
     *
     * @param extraClaims the additional claims
     * @param subject the subject (username)
     * @param expiration the expiration time in milliseconds
     * @return the JWT token
     */
    private String buildToken(Map<String, Object> extraClaims, String subject, long expiration) {
        log.debug("Building JWT token for subject: {}", subject);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates the given JWT token.
     *
     * @param token the JWT token
     * @return true if the token is valid, false otherwise
     */
    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            log.error("Invalid JWT token: {}", token, e);
            return false;
        }
    }

    /**
     * Checks if the given JWT token is expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    protected boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token the JWT token
     * @return the expiration date
     */
    protected Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param token the JWT token
     * @return the claims
     */
    protected Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Gets the signing key for JWT token generation and validation.
     *
     * @return the signing key
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}