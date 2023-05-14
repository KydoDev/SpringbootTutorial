package com.skia.lab.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.skia.lab.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

//  This class has 3 funtions:
//     - generate a JWT from username, date, expiration, secret
//     - get username from JWT
//     - validate a JWT



@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${skia.lab.jwtSecret}")
  private String jwtSecret;

  @Value("${skia.lab.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    byte[] signingKey = jwtSecret.getBytes();
    return Jwts.builder()
        .setSubject((userPrincipal.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512) //for JJWT 1.0 release
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    byte[] signingKey = jwtSecret.getBytes();
    return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody().getSubject();
    //The parseClaimsJws() method on the ParserBuilder object returns a Jws object that represents the signed JWT. 
    //You can then call getSignature() or getBody() on the Jws object to get the signature or body of the JWT respectively.
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    } catch (Exception e) { //SignatureException deprecated, and catch generic exception if not other
      logger.error("Invalid JWT generic(signature?): {}", e.getMessage());
    }
    
    return false;
  }
}
