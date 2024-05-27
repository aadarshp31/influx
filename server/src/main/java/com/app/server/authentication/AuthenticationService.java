package com.app.server.authentication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.server.common.CONSTANT;
import com.app.server.role.Role;
import com.app.server.role.RoleRepository;
import com.app.server.user.User;
import com.app.server.user.UserRepository;
import com.app.server.utils.CookieUtils;

import jakarta.transaction.Transactional;

/**
 * Service class to provide authentication related services. It contains methods
 * to interact with database for authentication related functionalities. All
 * methods in this class have transaction execution.
 * 
 * @author @aadarshp31
 */
@Service
@Transactional
public class AuthenticationService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private CookieUtils cookieUtils;

  /**
   * Service to create a new user. Stores the new user to database.
   * 
   * @param userSignupDTO User singup details
   * @return User details of the newly created user
   * @author @aadarshp31
   */
  public ResponseEntity<Object> registUser(UserSignupDTO userSignupDTO) {
    Map<String, Object> body = new HashMap<String, Object>();
    body.put("validationStatus", true);
    try {

      Boolean isUsernameExists = userRepository.findByUsername(userSignupDTO.username).isPresent();
      if (isUsernameExists) {
        String validationMessage = "Username already exists";
        body.put("validationStatus", false);
        body.put("username", validationMessage);
        throw new Exception(validationMessage);
      }

      Boolean isEmailExists = userRepository.findByEmail(userSignupDTO.email).isPresent();
      if (isEmailExists) {
        String validationMessage = "Email already exists";
        body.put("validationStatus", false);
        body.put("email", validationMessage);
        throw new Exception(validationMessage);
      }

      Role userRole = roleRepository.findByAuthority(CONSTANT.ROLE_USER).get();
      Set<Role> authorities = new HashSet<Role>();
      authorities.add(userRole);

      User user = new User(
          userSignupDTO.username,
          userSignupDTO.firstname,
          userSignupDTO.lastname,
          userSignupDTO.email,
          passwordEncoder.encode(userSignupDTO.password),
          authorities);

      User newlyCreatedUser = userRepository.save(user);
      String jwtToken = jwtService.generateToken(newlyCreatedUser);

      body.put("status", "success");
      body.put("message", "New user created successfully");
      body.put("user", newlyCreatedUser);
      body.put(CONSTANT.ACCESS_TOKEN, jwtToken);
      cookieUtils.setCookieValue(CONSTANT.ACCESS_TOKEN, jwtToken);
      return new ResponseEntity<Object>(body, HttpStatus.OK);

    } catch (Exception e) {
      body.put("status", "failure");
      body.put("message", e.getMessage());
      e.printStackTrace();
      return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Service to signin user with proper credentials.
   * 
   * @param userSigninDTO User signin details
   * @return User details of the signed in user and auth token
   * @author @aadarshp31
   */
  public ResponseEntity<Object> signin(UserSigninDTO userSigninDTO) {
    Map<String, Object> body = new HashMap<String, Object>();
    body.put("validationStatus", true);
    try {

      Boolean isUserExists = userRepository.findByUsername(userSigninDTO.username).isPresent();

      if (!isUserExists) {
        String validationMessage = "Username does not exists";
        body.put("status", false);
        body.put("validationStatus", false);
        body.put("username", validationMessage);
        body.put("message", validationMessage);
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
      }

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(userSigninDTO.username, userSigninDTO.password));

      User user = userRepository.findByUsername(userSigninDTO.username).get();
      String jwtToken = jwtService.generateToken(user);

      body.put("status", "success");
      body.put("message", "User signed in successfully");
      body.put("user", user);
      body.put(CONSTANT.ACCESS_TOKEN, jwtToken);
      cookieUtils.setCookieValue(CONSTANT.ACCESS_TOKEN, jwtToken);
      return new ResponseEntity<Object>(body, HttpStatus.OK);

    } catch (AuthenticationException e) {

      e.printStackTrace();
      String validationMessage = "Invalid username or password";
      body.put("validationStatus", false);
      body.put("username", validationMessage);
      body.put("password", validationMessage);
      body.put("status", "failure");
      body.put("message", "Invalid username or password");
      return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Service to logout user at client side via removing cookies.
   * 
   * @return logout success reponse object
   * @author @aadarshp31
   */
  public ResponseEntity<Object> logout() {
    Map<String, Object> body = new HashMap<String, Object>();
    cookieUtils.setCookieValue(CONSTANT.ACCESS_TOKEN, "deleted", 0);
    body.put("status", "success");
    body.put("message", "User logged out successfully");
    return new ResponseEntity<Object>(body, HttpStatus.OK);
  }
}
