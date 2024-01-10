package com.app.server.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface to interact with Roles stored in the database
 * 
 * @author @aadarshp31
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByAuthority(String authority);
}
