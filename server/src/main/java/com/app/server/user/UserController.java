package com.app.server.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Class for handling user related endpoints
 * 
 * @author @aadarshp31
 */
@RestController
@RequestMapping({ "/api/users", "/api/users/" })
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping({ "", "/" })
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping({ "/{username}" })
  public List<User> getUserByUsername(@PathVariable String username) {
    if (userRepository.findByUsername(username).isPresent()) {
      return List.of(userRepository.findByUsername(username).get());
    }

    return new ArrayList<User>();
  }

  @GetMapping({ "access", "access/" })
  public ResponseEntity<String> userAccess() {
    return ResponseEntity.ok("You have user access!");
  }
}
