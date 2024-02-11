package com.app.server.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.server.common.CONSTANT;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * Updates user details usign username
   * 
   * @param username    username of the user to be updated
   * @param newUserData data to be updated
   * @return updated user data
   * @throws EntityNotFoundException when no user with provided username is found
   *                                 in database
   * @throws AccessDeniedException   when current authenticated user does not have
   *                                 access to update requested user's profile
   * @author @aadarshp31
   */
  public User updateUser(String username, UpdateUserDTO newUserData)
      throws EntityNotFoundException, AccessDeniedException {

    // Get the authentication object from the SecurityContextHolder
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User loggedInUser = userRepository.findByUsername(authentication.getName()).get();
    List<String> rolesOfLoggedInUser = loggedInUser.getAuthorities()
        .stream()
        .map(role -> role.getAuthority())
        .collect(Collectors.toList());

    User user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);

    if (user == null) {
      throw new EntityNotFoundException("User not found with username: " + username);
    }

    if (newUserData.getFirstname() != null) {
      user.setFirstname(newUserData.getFirstname());
    }

    if (newUserData.getLastname() != null) {
      user.setLastname(newUserData.getLastname());
    }

    if (newUserData.getPassword() != null) {
      user.setPassword(passwordEncoder.encode(newUserData.getPassword()));
    }

    if (newUserData.getUsername() != null) {
      if (loggedInUser.getId() != user.getId()) {
        throw new AccessDeniedException("You are not authorized to modify username");
      }

      user.setUsername(newUserData.getUsername());
    }

    if (newUserData.getAuthorities() != null) {
      if (!rolesOfLoggedInUser.contains(CONSTANT.ROLE_ADMINISTRATOR)) {
        throw new AccessDeniedException("You are not authorized to modify authorities");
      }

      user.setAuthorities(newUserData.getAuthorities());
    }

    if (newUserData.getIsEnabled() != null) {
      if (!rolesOfLoggedInUser.contains(CONSTANT.ROLE_ADMINISTRATOR)) {
        throw new AccessDeniedException("You are not authorized to modify authorities");
      }

      user.setIsEnabled(newUserData.getIsEnabled());
    }

    if (newUserData.getIsAccountNonExpired() != null) {
      if (!rolesOfLoggedInUser.contains(CONSTANT.ROLE_ADMINISTRATOR)) {
        throw new AccessDeniedException("You are not authorized to modify authorities");
      }

      user.setIsAccountNonExpired(newUserData.getIsAccountNonExpired());
    }

    if (newUserData.getIsCredentialsNonExpired() != null) {
      if (!rolesOfLoggedInUser.contains(CONSTANT.ROLE_ADMINISTRATOR)) {
        throw new AccessDeniedException("You are not authorized to modify authorities");
      }

      user.setIsCredentialsNonExpired(newUserData.getIsCredentialsNonExpired());
    }

    User updatedUser = userRepository.save(user);
    return updatedUser;
  }

}
