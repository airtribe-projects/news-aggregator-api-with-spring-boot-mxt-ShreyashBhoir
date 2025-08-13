package com.aggregator.aggregator.controller;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aggregator.aggregator.configuration.JwtUtil;
import com.aggregator.aggregator.domain.User;
import com.aggregator.aggregator.repository.UserRepository;

/**
 * AuthController
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,
    JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      return ResponseEntity.badRequest().body("Username already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return ResponseEntity.ok("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User user) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
      );
      String token = jwtUtil.generateToken(user.getUsername());
      return ResponseEntity.ok(Map.of("token", token));
    } catch (Exception e) {
      return ResponseEntity.status(401).body("Invalid username or password");
    }
  }
}
