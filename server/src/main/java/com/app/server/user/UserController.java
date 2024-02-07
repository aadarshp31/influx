package com.app.server.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class for handling user related endpoints
 * 
 * @author @aadarshp31
 */
@RestController
@RequestMapping({ "/api/users", "/api/users/" })
public class UserController {

  @GetMapping({ "access", "access/" })
  public ResponseEntity<String> userAccess() {
    return ResponseEntity.ok("You have user access!");
  }
}