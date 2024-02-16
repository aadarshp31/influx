package com.app.server.authentication;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.server.utils.CookieUtils;

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
@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserDetailsService userDetailsService;

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String authorizationHeader = request.getHeader("Authorization");
      String cookieHeader = request.getHeader("Cookie");
      String token = null;

      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        token = authorizationHeader.split(" ")[1];
      } else if (cookieHeader != null) {
        String cookieToken = CookieUtils.getCookieValue(request, "token");
        token = cookieToken;
      } else {
        filterChain.doFilter(request, response);
        return;
      }

      if (token == null) {
        throw new AuthenticationException("Invalid authentication token");
      }

      String username = jwtService.extractUsername(token);

      if (username == null) {
        throw new AuthenticationException("Invalid authentication token");
      }

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (SecurityContextHolder.getContext().getAuthentication() == null) {
        if (!jwtService.isValidToken(token, userDetails)) {
          throw new AuthenticationException("Invalid authentication token");
        }

        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userAuthToken);
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      filterChain.doFilter(request, response);
      e.printStackTrace();
    }

  }

}
