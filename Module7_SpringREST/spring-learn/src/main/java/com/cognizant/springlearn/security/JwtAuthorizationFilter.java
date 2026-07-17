package com.cognizant.springlearn.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JWT-handson: "Authorize based on JWT"
 *
 * The original hands-on extends BasicAuthenticationFilter (javax.servlet,
 * Spring Security 5). Spring Security 6 / Boot 3 use jakarta.servlet and
 * favor a plain OncePerRequestFilter registered on the SecurityFilterChain
 * instead - same intercept-every-request idea, current API.
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private final JwtUtil jwtUtil;

    public JwtAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                     FilterChain chain) throws ServletException, IOException {
        LOGGER.info("Start");
        String header = request.getHeader("Authorization");
        LOGGER.debug("Authorization header: {}", header);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring("Bearer ".length());
            String user = jwtUtil.validateAndGetUser(token);
            if (user != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                LOGGER.debug("Authenticated user via JWT: {}", user);
            }
        }

        chain.doFilter(request, response);
        LOGGER.info("End");
    }
}
