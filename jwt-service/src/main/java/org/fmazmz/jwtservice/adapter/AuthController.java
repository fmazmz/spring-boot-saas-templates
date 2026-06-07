package org.fmazmz.jwtservice.adapter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fmazmz.jwtservice.adapter.requests.LoginRequest;
import org.fmazmz.jwtservice.adapter.requests.RegistrationRequest;
import org.fmazmz.jwtservice.application.UserAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserAuthService userAuthService;

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegistrationRequest request) {
        userAuthService.registerUser(request.username(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userAuthService.loginUser(request.username(), request.password()));
    }
}
