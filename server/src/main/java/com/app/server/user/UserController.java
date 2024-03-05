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

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping({ "", "/" })
  public ResponseEntity<Object> getAllUsers() {
    Map<String, Object> body = new HashMap<String, Object>();
    try {
      List<User> users = userService.getUsers();
      body.put("success", true);
      body.put("message", "users found");
      body.put("users", users);
      return new ResponseEntity<Object>(body, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      body.put("success", false);

      if (e instanceof AccessDeniedException) {
        body.put("message", e.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.FORBIDDEN);
      }

      body.put("message", "something went wrong");
      return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping({ "/{username}", "/{username}/" })
  public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
    Map<String, Object> body = new HashMap<String, Object>();
    try {
      List<User> users = userService.getUsers(username);
      body.put("success", true);
      body.put("message", "user found");
      body.put("users", users);

      return new ResponseEntity<Object>(body, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      body.put("success", false);

      if (e instanceof EntityNotFoundException) {
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
      }

      if (e instanceof AccessDeniedException) {
        body.put("message", e.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.FORBIDDEN);
      }

      body.put("message", "something went wrong");
      return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

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

  @DeleteMapping({ "/{username}", "/{username}/" })
  public ResponseEntity<Object> deleteUser(@PathVariable String username) {
    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", String.format("failed to delete user with username '%s'", username));
    try {
      boolean isSuccess = userService.deleteUser(username);

      if (!isSuccess) {
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
      }

      body.put("success", true);
      body.put("message", String.format("successfully deleted user with username '%s'", username));

      return new ResponseEntity<Object>(body, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();

      if (e instanceof EntityNotFoundException) {
        body.put("message", String.format("user with username '%s' does not exists", username));
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
      }

      if (e instanceof AccessDeniedException) {
        body.put("message", e.getMessage());
        return new ResponseEntity<Object>(body, HttpStatus.FORBIDDEN);
      }

      body.put("message", "something went wrong");
      return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping({ "/{username}/roles", "/{username}/roles/" })
  public ResponseEntity<Object> getRolesByUsername(@PathVariable String username) {
    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    try {
      List<String> roles = userService.getRolesByUsername(username);
      body.put("success", true);
      body.put("message", String.format("Found all roles for the user with username '%s'", username));
      body.put("roles", roles);
      return new ResponseEntity<Object>(body, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      body.put("message", e.getMessage());
      return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
    } catch (AccessDeniedException e) {
      body.put("message", e.getMessage());
      return new ResponseEntity<Object>(body, HttpStatus.FORBIDDEN);
    } catch (Exception e) {
      body.put("message", e.getMessage());
      return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
