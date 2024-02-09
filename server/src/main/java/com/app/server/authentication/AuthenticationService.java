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

  /**
   * Service to create a new user. Stores the new user to database.
   * 
   * @param userSignupDTO User singup details
   * @return User details of the newly created user
   * @author @aadarshp31
   */
  public ResponseEntity<Object> registUser(UserSignupDTO userSignupDTO) {
    Map<String, Object> body = new HashMap<String, Object>();
    try {

      Boolean isUsernameExists = userRepository.findByUsername(userSignupDTO.username).isPresent();
      if (isUsernameExists) {
        throw new Exception("Username already exists.");
      }

      Boolean isEmailExists = userRepository.findByEmail(userSignupDTO.email).isPresent();
      if (isEmailExists) {
        throw new Exception("Email already exists.");
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

      body.put("status", "success");
      body.put("message", "New user created successfully");
      body.put("user", newlyCreatedUser);
      return new ResponseEntity<Object>(body, HttpStatus.OK);

    } catch (Exception e) {
      body.put("status", "failure");
      body.put("message", e.getMessage());
      e.printStackTrace();
      return new ResponseEntity<Object>(body, HttpStatus.FORBIDDEN);
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
    try {

      Boolean isUserExists = userRepository.findByUsername(userSigninDTO.username).isPresent();

      if (!isUserExists) {
        body.put("status", false);
        body.put("message", String.format("User with username \"%s\" does not exist", userSigninDTO.username));
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
      }

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(userSigninDTO.username, userSigninDTO.password));

      User user = userRepository.findByUsername(userSigninDTO.username).get();
      String jwtToken = jwtService.generateToken(user);

      body.put("status", "success");
      body.put("message", "User signed in successfully");
      body.put("user", user);
      body.put("access_token", jwtToken);
      return new ResponseEntity<Object>(body, HttpStatus.OK);

    } catch (AuthenticationException e) {

      e.printStackTrace();
      body.put("status", "failure");
      body.put("message", "Invalid username or password");
      return new ResponseEntity<Object>(body, HttpStatus.FORBIDDEN);
    }
  }

}
