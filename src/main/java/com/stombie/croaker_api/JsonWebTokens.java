package com.stombie.croaker_api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

public class JsonWebTokens {
    public static String createToken(Authentication authentication, Algorithm algorithm, long lifeTime) {
        return JWT.create()
                .withSubject(authentication.getName())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + lifeTime))
                .withClaim("roles", authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
    }
}
