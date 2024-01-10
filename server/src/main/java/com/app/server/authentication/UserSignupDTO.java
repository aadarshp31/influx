package com.app.server.authentication;

/**
 * This class describes the properties required for a user signup process.
 * 
 * @author @aadarshp31
 */
public class UserSignupDTO {
  final String username;
  final String email;
  final String firstname;
  final String lastname;
  final String password;

  public UserSignupDTO(String username, String email, String firstname, String lastname, String password) {
    this.username = username;
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = password;
  }

}
