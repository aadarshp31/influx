package com.app.server.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
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

  /**
   * Gets all claims fron a JWT token
   * 
   * @param jwtToken JWT token
   * @return All claims present in the JWT token
   * @author @aadarshp31
   */
  public Claims extractAllClaims(String jwtToken) {
    return Jwts
        .parser()
        .verifyWith(getSecretKey())
        .build()
        .parseSignedClaims(jwtToken)
        .getPayload();
  }

  /**
   * Extracts s specific claim from JWT token using a resolver function
   * 
   * @param <T>           data type of the claim you want to extract
   * @param jwtToken      JWT token
   * @param claimResolver a function that helps to get a particular claim from a
   *                      list of claims
   * @return specific claim that the claimResolver function returns
   * @author @aadarshp31
   */
  public <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
    Claims claims = extractAllClaims(jwtToken);
    return claimResolver.apply(claims);
  }

  /**
   * Extracts username from the JWT token
   * 
   * @param jwtToken JWT token
   * @return username stored in the JWT token
   * @author @aadarshp31
   */
  public String extractUsername(String jwtToken) {
    return extractClaim(jwtToken, Claims::getSubject);
  }

  /**
   * Extracts expiry from the JWT token
   * 
   * @param jwtToken JWT token
   * @return expiry time of the JWT token
   * @author @aadarshp31
   */
  public Date extractExpiry(String jwtToken) {
    return extractClaim(jwtToken, Claims::getExpiration);
  }

  /**
   * Checks whether the provided JWT token is valid or not
   * 
   * @param jwtToken    JWT token
   * @param userDetails userDetails object of the requesting user
   * @return boolean indicating whether the provided JWT token is valid or not
   * @author @aadarshp31
   */
  public Boolean isValidToken(String jwtToken, UserDetails userDetails) {
    String subject = extractUsername(jwtToken);
    return userDetails.getUsername().equals(subject);
  }

}