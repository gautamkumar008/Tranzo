package com.Tranzo.Security;

import com.Tranzo.Model.Company;
import com.Tranzo.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "cWkev7VAiVv3OX9yl3WTJCZH9qnZccK6Tsy7X32sBqQ=";

    // 🔐 USER TOKEN
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name()) // ADMIN / USER / COMPANY
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(
                        Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    // 🔐 COMPANY TOKEN
    public String generateCompanyToken(Company company) {
        return Jwts.builder()
                .setSubject(company.getCompanyEmail())
                .claim("role", company.getRole().name())
                .claim("companyId", company.getCid())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
