package com.app.server.authentication;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A filter class that runs once for every request and handles jwt token related
 * auth
 * handling
 * 
 * @author @aadarshp31
 */
public class JwtSecurityFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String authorizationHeader = request.getHeader("Authorization");

      if (authorizationHeader.equals(null) && !authorizationHeader.startsWith("Bearer ")) {
        throw new AuthenticationException("Request is missing or invalid authorization header");
      }

      String token = authorizationHeader.split(" ")[1];

      if (token.equals(null)) {
        throw new AuthenticationException("Invalid authentication token");
      }

      String username = jwtService.extractUsername(token);

      if (username.equals(null)) {
        throw new AuthenticationException("Invalid authentication token");
      }

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (SecurityContextHolder.getContext().getAuthentication().equals(null)) {

        if (!jwtService.isValidToken(token, userDetails)) {
          throw new AuthenticationException("Invalid authentication token");
        }

        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(username,
            userDetails);
        SecurityContextHolder.getContext().setAuthentication(userAuthToken);
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      filterChain.doFilter(request, response);
      e.printStackTrace();
    }

  }

}
