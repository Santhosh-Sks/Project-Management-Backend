package com.project_manage.projectmanagementsystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

public class JwtProvider {

    private static final String SECRET_KEY = "your-very-secure-secret-key-should-be-long"; // Use env variables or properties
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private static final long EXPIRATION_TIME = 864000000; // 10 days

    public static String generateToken(Authentication auth) {
        String email = auth.getName();
        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("email", email)
                .claim("roles", authorities)
                .signWith(key)
                .compact();
    }

    public static String getEmailFromToken(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.get("email", String.class);
    }

    public static List<GrantedAuthority> getRolesFromToken(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        List<String> roles = claims.get("roles", List.class);
        return roles != null ? roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()) : Collections.emptyList();
    }

    public static Authentication getAuthentication(String jwt) {
        String email = getEmailFromToken(jwt);
        List<GrantedAuthority> authorities = getRolesFromToken(jwt);

        return new UsernamePasswordAuthenticationToken(email, null, authorities);
    }

}
