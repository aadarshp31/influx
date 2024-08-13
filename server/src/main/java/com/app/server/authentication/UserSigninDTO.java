package com.app.server.authentication;

import io.micrometer.common.lang.NonNull;

/**
 * This class describes the properties required for a user signin process.
 * 
 * @author @aadarshp31
 */
public class UserSigninDTO {
  @NonNull
  final String username;
  final String password;

  public UserSigninDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

}
