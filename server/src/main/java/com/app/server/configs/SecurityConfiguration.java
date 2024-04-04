package com.app.server.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.server.authentication.JwtSecurityFilter;
import com.app.server.common.CONSTANT;

/**
 * A class to contain all the security related configurations for the app
 * 
 * @author @aadarshp31
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final AuthenticationProvider authenticationProvider;
  private final JwtSecurityFilter jwtSecurityFilter;

  public SecurityConfiguration(AuthenticationProvider authenticationProvider, JwtSecurityFilter jwtSecurityFilter) {
    this.authenticationProvider = authenticationProvider;
    this.jwtSecurityFilter = jwtSecurityFilter;
  }

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
              .requestMatchers("/api", "/api/", "/api/auth/signin", "/api/auth/signin/", "/api/auth/signup",
                  "/api/auth/signup/")
              .permitAll()

              .requestMatchers("/api/admin/**")
              .hasAuthority(CONSTANT.ROLE_ADMINISTRATOR)

              .requestMatchers(HttpMethod.GET, "/api/users", "/api/users/")
              .hasAuthority(CONSTANT.ROLE_ADMINISTRATOR)

              .requestMatchers(HttpMethod.PUT, "/api/users", "/api/users/**", "/api/auth/logout", "/api/auth/logout/")
              .hasAnyAuthority(CONSTANT.ROLE_USER, CONSTANT.ROLE_ADMINISTRATOR)

              .requestMatchers(HttpMethod.DELETE, "/api/users", "/api/users/**")
              .hasAnyAuthority(CONSTANT.ROLE_ADMINISTRATOR)

              .requestMatchers("/api/users/**")
              .hasAuthority(CONSTANT.ROLE_USER)

              .anyRequest()
              .authenticated();
        });
    http.authenticationProvider(authenticationProvider);
    http.addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}
