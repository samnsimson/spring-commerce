package com.ecommerce.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
    private final String secretKeyString = "v8Ff2CjEWZ+Sdl85tjW2Jn0pIKqPSdj9BOgY6IVlac";
    private final SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(secretKeyString), "HmacSHA256");

    public String generateToken(String username){
        Date issuedDate = new Date(System.currentTimeMillis());
        Date validTill = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);
        return Jwts.builder().setSubject(username).setIssuedAt(issuedDate).setExpiration(validTill).signWith(secretKey).compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
