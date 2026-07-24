package com.cognizant.springlearn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JWT-handson: "Generate token based on the user" + "Authorize based on JWT"
 *
 * Rewritten for JJWT 0.12.x / Spring Boot 3 (the original hands-on doc uses
 * the much older jjwt 0.9.0 API - Jwts.builder().setSubject(...) and
 * signWith(SignatureAlgorithm.HS256, "secretkey") - which no longer compiles
 * against modern JJWT or Spring Security 6).
 */
@Component
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    /** 20 minutes, same expiry the hands-on doc uses. */
    private static final long EXPIRATION_MILLIS = 1_200_000;

    private final SecretKey signingKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // HS256 needs a key of at least 256 bits - pad/hash the configured secret to size.
        this.signingKey = Keys.hmacShaKeyFor(pad(secret));
    }

    private byte[] pad(String secret) {
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        if (bytes.length >= 32) {
            return bytes;
        }
        byte[] padded = new byte[32];
        System.arraycopy(bytes, 0, padded, 0, bytes.length);
        return padded;
    }

    /** JWT-handson: "Generate token based on the user" */
    public String generateToken(String user) {
        LOGGER.info("Start");
        String token = Jwts.builder()
                .subject(user)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(signingKey)
                .compact();
        LOGGER.info("End");
        return token;
    }

    /** JWT-handson: "Authorize based on JWT" - returns the subject (user) if valid, null otherwise. */
    public String validateAndGetUser(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException ex) {
            LOGGER.debug("Invalid JWT: {}", ex.getMessage());
            return null;
        }
    }
}
