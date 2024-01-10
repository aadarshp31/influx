package com.app.server.role;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Model class to contain User's role details
 * 
 * @author @aadarshp31
 */
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long role_id;
  private String authority;

  @Override
  public String getAuthority() {
    return this.authority;
  }

  public Role() {

  }

  public Role(String authority) {
    this.authority = authority;
  }

}
