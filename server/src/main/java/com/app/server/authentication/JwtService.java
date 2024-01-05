package com.app.server.authentication;

import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * This class contains methods for handling JWT tokens
 * 
 * @author @aadarshp31
 */
public class JwtService {

  @Value("${influx.jwt-secret-key}")
  String JWT_SECRET_KEY;

  /**
   * Gets secret key for signing jwt tokens
   * 
   * @return instance of SecretKey
   * @author @aadarshp31
   */
  public SecretKey getSecretKey() {
    byte[] encodedByteArray = Decoders.BASE64.decode(JWT_SECRET_KEY);
    return Keys.hmacShaKeyFor(encodedByteArray);
  }

  /**
   * Generates JWT token for a user
   * 
   * @param userDetails details of the user (subject)
   * @return JWT token
   * @author @aadarshp31
   */
  public String generateToken(UserDetails userDetails) {
    return generateToken(userDetails, new HashMap<String, Object>());
  }

  /**
   * Generates JWT token with extra/custom claims for a user
   * 
   * @param userDetails details of the user (subject)
   * @return JWT token
   * @author @aadarshp31
   */
  public String generateToken(UserDetails userDetails, HashMap<String, Object> extraClaims) {
    return Jwts
        .builder()
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
        .subject(userDetails.getUsername())
        .claims(extraClaims)
        .signWith(getSecretKey(), SIG.HS256)
        .compact();
  }

}