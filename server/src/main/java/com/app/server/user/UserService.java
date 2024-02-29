package com.app.server.user;

import java.util.ArrayList;
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
import com.app.server.role.Role;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * Gets list of users from the database
   * 
   * @return List of users
   * @throws AccessDeniedException
   * @author @aadarshp31
   */
  public List<User> getUsers() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User loggedInUser = userRepository.findByUsername(authentication.getName()).get();
    List<String> rolesOfLoggedInUser = loggedInUser.getAuthorities()
        .stream()
        .map(role -> role.getAuthority())
        .collect(Collectors.toList());

    if (!rolesOfLoggedInUser.contains(CONSTANT.ROLE_ADMINISTRATOR)) {
      throw new AccessDeniedException("You are not authorized to access this resource");
    }

    return userRepository.findAll();
  }

  /**
   * Gets a user by username from the database
   * 
   * @param username username of the user to be fetched
   * @return List of users
   * @throws EntityNotFoundException
   * @throws AccessDeniedException
   * @author @aadarshp31
   */
  public List<User> getUsers(String username) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User loggedInUser = userRepository.findByUsername(authentication.getName()).get();
    List<String> rolesOfLoggedInUser = loggedInUser.getAuthorities()
        .stream()
        .map(role -> role.getAuthority())
        .collect(Collectors.toList());

    if (!userRepository.findByUsername(username).isPresent()) {
      throw new EntityNotFoundException("User not found with username: " + username);
    }

    User user = userRepository.findByUsername(username).get();

    if (!rolesOfLoggedInUser.contains(CONSTANT.ROLE_ADMINISTRATOR) && loggedInUser.getId() != user.getId()) {
      throw new AccessDeniedException("You are not authorized to access this resource");
    }

    return List.of(user);
  }

  /**
   * Updates user details using username
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

  /**
   * Deletes a user using username
   * 
   * @param username username of the user to be updated
   * @return boolean indicating whether the user has been deleted successfully
   * @throws EntityNotFoundException when no user with provided username is found
   *                                 in database
   * @throws AccessDeniedException   when current authenticated user does not have
   *                                 access to delete requested user's profile
   * @author @aadarshp31
   */
  public boolean deleteUser(String username) throws EntityNotFoundException, AccessDeniedException {
    boolean isSuccess = false;
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

    if (!rolesOfLoggedInUser.contains(CONSTANT.ROLE_ADMINISTRATOR)) {
      throw new AccessDeniedException("You are not allowed to delete a user.");
    }

    userRepository.delete(user);
    isSuccess = true;
    return isSuccess;
  }

  /**
   * Gets roles for a particular user having given username
   * 
   * @param username username of the requested user
   * @return list of roles assigned to the user
   * @author @aadarshp31
   * @throws EntityNotFoundException when there is no user with given username
   * @throws AccessDeniedException   when requesting user is not the same as the
   *                                 requested resource or doesn't have admin
   *                                 access
   */
  public List<Role> getRolesByUsername(String username) throws EntityNotFoundException, AccessDeniedException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User loggedInUser = userRepository.findByUsername(authentication.getName()).get();
    List<String> rolesOfLoggedInUser = loggedInUser.getAuthorities()
        .stream()
        .map(role -> role.getAuthority())
        .collect(Collectors.toList());

    if (username != loggedInUser.getUsername() || !rolesOfLoggedInUser.contains(CONSTANT.ROLE_ADMINISTRATOR)) {
      throw new AccessDeniedException("You are not authorized to access this resource");
    }

    User user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);

    return new ArrayList<Role>(user.getAuthorities());
  }

}
