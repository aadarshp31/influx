package com.app.server.user;

import java.util.Set;

import com.app.server.role.Role;

/**
 * DTO class for update user api endpoint
 * 
 * @author @aadarshp31
 */
public class UpdateUserDTO {
  private Long id;
  private String username;
  private String email;
  private Set<Role> authorities;
  private String firstname;
  private String lastname;
  private String password;
  private Boolean isAccountNonExpired;
  private Boolean isCredentialsNonExpired;
  private Boolean isEnabled;

  public UpdateUserDTO() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Role> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Role> authorities) {
    this.authorities = authorities;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getIsAccountNonExpired() {
    return isAccountNonExpired;
  }

  public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
    this.isAccountNonExpired = isAccountNonExpired;
  }

  public Boolean getIsCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }

  public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
    this.isCredentialsNonExpired = isCredentialsNonExpired;
  }

  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

}
