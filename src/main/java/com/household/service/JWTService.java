package com.household.service;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Duration;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    @Value("${JWT.ISSUER}")
    private String ISSUER;

    @Value("${JWT.SECRET}")
    private String SECRET;

    private final String USER_KEY = "userId";

    public String createToken(final long userId) {
        Date nowDate = new Date();

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(ISSUER)
            .setIssuedAt(nowDate)
            .setExpiration(new Date(nowDate.getTime() + Duration.ofMinutes(30).toMillis()))
            .claim(USER_KEY, userId)
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    public Long parseJwtToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }

        return (Long) Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token.substring("Bearer ".length()))
            .getBody()
            .get(USER_KEY);
    }
}
