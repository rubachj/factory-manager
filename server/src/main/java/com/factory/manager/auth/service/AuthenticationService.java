package com.factory.manager.auth.service;

import com.factory.manager.auth.dto.AuthenticateRequestDto;
import com.factory.manager.auth.dto.AuthenticationResponseDto;

public interface AuthenticationService {

    AuthenticationResponseDto authenticate(AuthenticateRequestDto request);

}
