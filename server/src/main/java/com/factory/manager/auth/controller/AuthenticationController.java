package com.factory.manager.auth.controller;

import com.factory.manager.auth.dto.AuthenticateRequestDto;
import com.factory.manager.auth.dto.AuthenticationResponseDto;
import com.factory.manager.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticateRequestDto request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
