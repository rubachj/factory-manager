package com.factory.manager.core.jwt.filter;

import com.factory.manager.core.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final static String AUTHORIZATION = "Authorization";
    private final static String BEARER = "Bearer ";
    private final static Integer INDEX_FIRST_CHAR_AUTH_HEADER = 7;

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var authHeader = request.getHeader(AUTHORIZATION);
        if (isNotAuthHeader(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        authenticationUser(request, authHeader);
        filterChain.doFilter(request, response);
    }

    private boolean isNotAuthHeader(String authHeader) {
        return Objects.isNull(authHeader) || !authHeader.startsWith(BEARER);
    }

    private void authenticationUser(HttpServletRequest request, String authHeader) {
        final var jwt = authHeader.substring(INDEX_FIRST_CHAR_AUTH_HEADER);
        final var userEmail = jwtService.extractUsername(jwt);

        if (isNotAuthenticationUser(userEmail)) {
            final var userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                doAuthentication(request, userDetails);
            }
        }
    }

    private boolean isNotAuthenticationUser(String userEmail) {
        return Objects.nonNull(userEmail) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private void doAuthentication(HttpServletRequest request, UserDetails userDetails) {
        final var authToken = createUsernamePasswordAuthenticationToken(userDetails);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private UsernamePasswordAuthenticationToken createUsernamePasswordAuthenticationToken(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
