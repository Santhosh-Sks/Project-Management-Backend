package com.project_manage.projectmanagementsystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

import static com.project_manage.projectmanagementsystem.config.JwtConstant.SECRET_KEY;

public class JwtProvider {


    static SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        String jwt =Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+86400000)).claim("email",auth.getName()).signWith(key).compact();

        return jwt;
    }

    public static String getEmailFromToken(String jwt) {

        jwt= jwt.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return String.valueOf(claims.get("email"));
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
