package com.stombie.croaker_api;

import com.auth0.jwt.algorithms.Algorithm;

public class Constants {
    public static final String SECRET = "ficko1234";
    public static final long ACCESS_TOKEN_LIFETIME_IN_MILLIS = 1000 * 60 * 60;
    public static final long REFRESH_TOKEN_LIFETIME_IN_MILLIS = ACCESS_TOKEN_LIFETIME_IN_MILLIS * 1000;
    public static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET.getBytes());
    public static final String[] ALLOWED_ORIGINS = {
        "http://localhost:3000/"
    };
}
