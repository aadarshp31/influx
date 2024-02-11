package com.app.server.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

  @Autowired
  private UserService userService;

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

  @PutMapping({ "/{username}", "/{username}/" })
  public ResponseEntity<Object> updateUser(@PathVariable String username, @RequestBody UpdateUserDTO entity) {
    Map<String, Object> body = new HashMap<String, Object>();

    try {
      User updatedUser = userService.updateUser(username, entity);
      body.put("success", true);
      body.put("message", "user updated successfully");
      body.put("users", List.of(updatedUser));
      return new ResponseEntity<Object>(body, HttpStatus.OK);
    } catch (Exception e) {
      body.put("success", false);
      body.put("message", "failed to update user");

      // handle exceptions EntityNotFoundException, AccessDeniedException
      if (e instanceof AccessDeniedException) {
        e.printStackTrace();
        return new ResponseEntity<Object>(body, HttpStatus.FORBIDDEN);
      }

      if (e instanceof EntityNotFoundException) {
        body.put("message", String.format("user with username %s does not exists", username));
        e.printStackTrace();
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
      }

      e.printStackTrace();
      return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }

  }

}
