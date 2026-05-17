package com.spring.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private String secureKey = "1222";

    public JWTService() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");

            // another way aGVsbG9zaGFyaW5nc3ByaW5nc2VjdXJpdHlqd3R0b2tlbmRlbW8= and make some changes
            SecretKey key = keyGenerator.generateKey();

            secureKey = Base64.getEncoder()
                    .encodeToString(key.getEncoded());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String name) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(name)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secureKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    // extract username from token
    public String extractToken(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // generic method for extracting claims
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {

        final Claims claims = extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    // extract all claims
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // check expiration
    private boolean isTokenExpired(String token) {

        return extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }

    // validate token
    public boolean validToken(String token, UserDetails userDetails) {

        final String username = extractToken(token);

        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }
}