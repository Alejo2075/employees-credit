package org.alejo2075.employees_credit.modules.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationRequest;
import org.alejo2075.employees_credit.modules.user.model.dto.AuthenticationResponse;
import org.alejo2075.employees_credit.modules.user.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Log4j2
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user and returns JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid user details")
    })
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody @Valid AuthenticationRequest request) {
        log.info("Registering user {}", request.getEmail());
        AuthenticationResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate a user and returns JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody @Valid AuthenticationRequest request) {
        log.info("Attempting to login user {}", request.getEmail());
        AuthenticationResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
