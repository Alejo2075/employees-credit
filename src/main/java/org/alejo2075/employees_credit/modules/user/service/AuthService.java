package org.alejo2075.employees_credit.modules.user.service;

import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse register(AuthenticationRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
