package org.alejo2075.employees_credit.modules.user.service;

import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationResponse;

/**
 * Service interface for authentication-related operations.
 */
public interface AuthService {

    /**
     * Registers a new user.
     *
     * @param request the authentication request containing user details
     * @return the authentication response containing the JWT token
     */
    AuthenticationResponse register(AuthenticationRequest request);

    /**
     * Authenticates a user.
     *
     * @param request the authentication request containing user credentials
     * @return the authentication response containing the JWT token
     */
    AuthenticationResponse login(AuthenticationRequest request);
}