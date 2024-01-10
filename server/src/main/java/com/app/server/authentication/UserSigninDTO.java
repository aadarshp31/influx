package com.app.server.authentication;

/**
 * This class describes the properties required for a user signin process.
 * 
 * @author @aadarshp31
 */
public class UserSigninDTO {
  final String username;
  final String password;

  public UserSigninDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

}
