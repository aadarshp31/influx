package com.app.server.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contains auth related api controller methods
 * 
 * @author @aadarshp31
 */
@RestController
@RequestMapping({ "/api/auth", "/api/auth/" })
@CrossOrigin(origins = "*")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  /**
   * Controller method to signup a user.
   * 
   * @param userSignupDTO User signup details
   * @return Details of the newly created user
   * @author @aadarshp31
   */
  @PostMapping({ "/signup", "/signup/" })
  public ResponseEntity<Object> signup(@RequestBody UserSignupDTO userSignupDTO) {
    return authenticationService.registUser(userSignupDTO);
  }

  /**
   * Controller method to signin a user.
   * 
   * @param userSignupDTO User signin details
   * @return Details of the signed in user and auth token
   * @author @aadarshp31
   */
  @PostMapping({ "/signin", "/signin/" })
  public ResponseEntity<Object> signin(@RequestBody UserSigninDTO userSigninDTO) {
    return authenticationService.signin(userSigninDTO);
  }

  /**
   * Controller method to logout user.
   * 
   * @return Logout success respons onject
   * @author @aadarshp31
   */
  @GetMapping({ "/logout", "/logout/" })
  public ResponseEntity<Object> logout() {
    return authenticationService.logout();
  }

}
