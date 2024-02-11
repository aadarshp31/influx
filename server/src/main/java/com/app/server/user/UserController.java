package com.app.server.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
    Map<String, Object> body = new HashMap<String, Object>();

    if (!userRepository.findByUsername(username).isPresent()) {
      return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    body.put("success", false);
    body.put("message", "user found");
    return new ResponseEntity<Object>(List.of(userRepository.findByUsername(username).get()), HttpStatus.OK);
  }

  @GetMapping({ "access", "access/" })
  public ResponseEntity<String> userAccess() {
    return ResponseEntity.ok("You have user access!");
  }
}
