package com.app.server.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.app.server.common.CONSTANT;
import com.app.server.user.UserRepository;

/**
 * Configuration class to contains configuration and standalone beans
 * 
 * @author @aadarshp31
 */
@Configuration
public class ApplicationConfiguration {

  @Autowired
  private UserRepository userRepository;

  /**
   * PasswordEncoder implementation bean
   * 
   * @return implementation of PasswordEncoder
   * @author @aadarshp31
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * userDetailsService is used by spring security auth providers to get the
   * user's details using username from the database on every request
   * 
   * @return A bean of type UserDetailsService
   * @author @aadarshp31
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found!", username)));
  }

  /**
   * Implementation of AuthenticationProvider used by spring security
   * 
   * @return A bean of type AuthenticationProvider
   * @author @aadarshp31
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService());
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

  /**
   * Implementation of AuthenticationManager used by spring security
   * 
   * @param config intance of AuthenticationConfiuration
   * @return A bean of type AuthenticationManager
   * @throws Exception throws exceptions if user authentication fails for some
   *                   reason
   * @author @aadarshp31
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(customizer -> customizer.disable())
        .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(customizer -> {
          customizer
              .requestMatchers("/api/auth**")
              .permitAll()

              .requestMatchers("/api/admin**")
              .hasRole(CONSTANT.ROLE_ADMINISTRATOR)

              .requestMatchers("/api/users**")
              .hasRole(CONSTANT.ROLE_USER)

              .anyRequest()
              .authenticated();
        });

    return http.build();
  }

}
