package com.app.server.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface to interact with Users stored in the database
 * 
 * @author @aadarshp31
 */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);
}
