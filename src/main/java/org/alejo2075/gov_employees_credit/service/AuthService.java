package org.alejo2075.gov_employees_credit.service;

import org.alejo2075.gov_employees_credit.model.dto.AuthenticationRequest;
import org.alejo2075.gov_employees_credit.model.dto.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse register(AuthenticationRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
