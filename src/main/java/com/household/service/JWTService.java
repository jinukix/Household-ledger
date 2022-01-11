package com.household.service;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Duration;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class JWTService {

    private final String USER_KEY = "userId";

    @Value("${JWT.ISSUER}")
    private String ISSUER;

    @Value("${JWT.SECRET}")
    private String SECRET;

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

    public Long loginAuth() {
        ServletRequestAttributes requestAttributes =
            (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        String token = requestAttributes.getRequest().getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }

        return ((Number) Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token.substring("Bearer ".length()))
            .getBody()
            .get(USER_KEY)).longValue();
    }
}
