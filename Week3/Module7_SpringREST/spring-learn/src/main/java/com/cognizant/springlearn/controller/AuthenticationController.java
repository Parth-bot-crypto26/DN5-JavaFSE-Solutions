package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT-handson: "Create authentication service that returns JWT"  ⭐ MANDATORY
 *
 * Request:  curl -s -u user:pwd http://localhost:8083/authenticate
 * Response: {"token":"eyJhbGciOiJIUzI1NiJ9...."}
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Start");
        LOGGER.debug("authHeader: {}", authHeader);

        String user = getUser(authHeader);
        String token = jwtUtil.generateToken(user);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("End");
        return map;
    }

    /** "Read Authorization header and decode the username and password" */
    private String getUser(String authHeader) {
        LOGGER.debug("Decoding Authorization header");
        String encodedCredentials = authHeader.replace("Basic ", "");
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decoded = new String(decodedBytes);
        String user = decoded.substring(0, decoded.indexOf(':'));
        LOGGER.debug("Decoded user: {}", user);
        return user;
    }
}
