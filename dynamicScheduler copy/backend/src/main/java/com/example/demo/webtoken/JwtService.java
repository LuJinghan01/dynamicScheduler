package com.example.demo.webtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final String SECRET = "725D8EE9CD9B6DD3B1E256892EC107B122DA764FCA19B6A1BC0ED428E4D4F46933B8E383C1270F4592AF47DF26038CDE0A57BB0990D2F257F52CD16CC9AF735E";
    private static final long VALIDITY = TimeUnit.MINUTES.toMillis(30);  // how long the token is valid

    // generate JWT token for logged in users
    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("iss", "https://www.youtube.com"); // "iss": issuer
        // claims.put("name", "");
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(generateKey())
                .compact();  // convert to json format
    }

    // convert decoded key to secret key
    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
                 .verifyWith(generateKey())
                 .build()
                 .parseSignedClaims(jwt)
                 .getPayload();
    }

    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}