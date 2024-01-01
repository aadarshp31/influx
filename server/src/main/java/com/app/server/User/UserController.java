package com.app.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping({ "/api/users", "/api/users/" })
public class UserController {

  @GetMapping({ "access", "access/" })
  public ResponseEntity<String> userAccess() {
    return ResponseEntity.ok("You have user access!");
  }
}
