package com.aggregator.aggregator.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

  // Use a 64-byte secret key for HS256
  private final SecretKey secretKey = Keys.hmacShaKeyFor(
    "thisisaverysecuresecretkeythisisaverysecuresecretkey".getBytes(StandardCharsets.UTF_8)
  );

  private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

  public String generateToken(String username) {
    return Jwts.builder()
      .subject(username)
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
      .signWith(secretKey)
      .compact();
  }

  public String extractUsername(String token) {
    return getClaims(token).getSubject();
  }

  public boolean isTokenValid(String token, String username) {
    return username.equals(extractUsername(token)) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  private Claims getClaims(String token) {
    return Jwts.parser()
      .verifyWith(secretKey)
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }
}