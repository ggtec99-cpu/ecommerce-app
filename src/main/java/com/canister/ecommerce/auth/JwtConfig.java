package com.canister.ecommerce.auth;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtConfig {


    @Value("${jwt.token.authToken}")
    private String jwtAuthToken;

    @Value("${jwt.token.tempToken}")
    private String jwtTempToken;


    @Value("${jwt.time.authTokenValidationTime}")
    private Long authTokenValidationTime;

    @Value("${jwt.time.tempTokenValidationTime}")
    private Long tempTokenValidationTime;



    private SecretKey getKey(String key){
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    private Date getExpirationTime(Long minutes){
        return new Date(System.currentTimeMillis() + minutes * 60 * 1000);
    }

    public String generateAuthToken(String payload){

        return Jwts.builder()
                .subject(payload)
                .expiration(getExpirationTime(authTokenValidationTime))
                .signWith(getKey(jwtAuthToken))
                .compact();
    }

    public String generateTempToken(String payload){
        return Jwts.builder()
                .subject(payload)
                .expiration(getExpirationTime(tempTokenValidationTime))
                .signWith(getKey(jwtTempToken))
                .compact();
    }


    public String validateAuthToken(String token) throws  JwtException{
        return validateToken(token,getKey(jwtAuthToken));
    }
    public String validateTempToken(String token) throws  JwtException{
        return validateToken(token,getKey(jwtAuthToken));
    }



    private String validateToken(String token, SecretKey key) throws JwtException {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(token)
                 .getPayload()
                .toString();
    }

}
