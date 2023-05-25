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

import static com.factory.manager.core.constants.Endpoints.AUTH;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticateRequestDto request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
