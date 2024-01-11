package com.app.server.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.app.server.common.CONSTANT;

/**
 * A class to contain all the security related configurations for the app
 * 
 * @author @aadarshp31
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

  /**
   * This is our custom SecurityFilterChain to handle access of api endpoints
   * 
   * @param http HttpSecurity object
   * @return Object of customised SecurityFilterChain class
   * @throws Exception
   * @author @aadarshp31
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(customizer -> customizer.disable())
        .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(customizer -> {
          customizer
              .requestMatchers("/api/auth/**")
              .permitAll()

              .requestMatchers("/api/admin/**")
              .hasRole(CONSTANT.ROLE_ADMINISTRATOR)

              .requestMatchers("/api/users/**")
              .hasRole(CONSTANT.ROLE_USER)

              .anyRequest()
              .authenticated();
        });

    return http.build();
  }
}
