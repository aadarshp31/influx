package com.app.server.user;

import com.app.server.common.CONSTANT;
import com.app.server.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Model class to contain user's account information
 * 
 * @author @aadarshp31
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long user_id;
  @Column(name = "username", unique = true)
  private String username;
  @Column(name = "email", unique = true)
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = CONSTANT.USER_ROLE_JUNCTION_TABLE_NAME, joinColumns = {
      @JoinColumn(name = "user_id") }, inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> authorities;

  private String firstname;
  private String lastname;
  @JsonIgnore
  private String password;
  private Boolean isAccountNonExpired;
  private Boolean isCredentialsNonExpired;
  private Boolean isEnabled;

  public User() {

  }

  public User(String username, String firstname, String lastname, String email, String password,
      Set<Role> authorities) {
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = password;
    this.email = email;
    this.authorities = authorities;
    this.isAccountNonExpired = true;
    this.isCredentialsNonExpired = true;
    this.isEnabled = true;
  }

  public Long getId() {
    return user_id;
  }

  public void setId(Long user_id) {
    this.user_id = user_id;
  }

  @Override
  public Set<Role> getAuthorities() {
    return this.authorities;
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

  @Override
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getUsername() {
    return this.username;
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

  @Override
  public boolean isAccountNonExpired() {
    return this.isAccountNonExpired;
  }

  public boolean setIsAccountNonExpired(boolean isAccountNonExpired) {
    return this.isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.isAccountNonExpired;
  }

  public boolean setIsAccountNonLocked(boolean isAccountNonExpired) {
    return this.isAccountNonExpired;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.isCredentialsNonExpired;
  }

  public boolean setIsCredentialsNonExpired(boolean isCredentialsNonExpired) {
    return this.isCredentialsNonExpired = isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }

  public boolean setIsEnabled(boolean isEnabled) {
    return this.isEnabled = isEnabled;
  }

}
