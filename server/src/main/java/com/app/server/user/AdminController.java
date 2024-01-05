package com.app.server.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class for handling admin authorized endpoints
 * 
 * @author @aadarshp31
 */
@RestController
@RequestMapping({ "/api/admin", "/api/admin/" })
public class AdminController {

  @GetMapping({ "access", "access/" })
  public ResponseEntity<String> adminAccess() {
    return ResponseEntity.ok("You have admin access!");
  }
}
