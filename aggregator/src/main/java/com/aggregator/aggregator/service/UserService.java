package com.aggregator.aggregator.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aggregator.aggregator.domain.User;
import com.aggregator.aggregator.repository.UserRepository;

/**
 * UserService
 * This class is a placeholder for user-related services.
 * It can be expanded with methods for user management, such as creating, updating, and deleting
 */
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User findByUsername(String username) {
    Optional<User> userOpt = userRepository.findByUsername(username);
    return userOpt.orElseThrow(() -> new RuntimeException("User not found: " + username));
  }

  public User save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public void updateNewsPreference(String username, String category) {
    User user = findByUsername(username);
    user.setNewsPreference(category);
    userRepository.save(user);
  }
}

