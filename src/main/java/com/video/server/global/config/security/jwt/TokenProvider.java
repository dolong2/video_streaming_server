package com.video.server.global.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000;
    private final long REFRESH_TOKEN_EXPIRE_TIME = ACCESS_TOKEN_EXPIRE_TIME / 3 * 24 * 30 * 6;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @AllArgsConstructor
    private enum TokenType{
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken");
        String value;
    }

    @AllArgsConstructor
    private enum TokenClaimName{
        USER_EMAIL("userEmail"),
        TOKEN_TYPE("tokenType");
        String value;
    }

    private Key getSignInKey(String secretKey){
        byte[] bytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    public Claims extractAllClaims(String token){
        token=token.replace("Bearer ", "");
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserEmail(String token){
        return extractAllClaims(token).get(TokenClaimName.USER_EMAIL.value, String.class);
    }

    public String getTokenType(String token){
        return extractAllClaims(token).get(TokenClaimName.TOKEN_TYPE.value, String.class);
    }

    public Boolean isTokenExpired(String token){
        try{
            extractAllClaims(token).getExpiration();
            return false;
        }catch (ExpiredJwtException e){
            return true;
        }
    }

    private String generateToken(String userEmail, TokenType tokenType, long expireTime){
        final Claims claims = Jwts.claims();
        claims.put(TokenClaimName.USER_EMAIL.value, userEmail);
        claims.put(TokenClaimName.TOKEN_TYPE.value, tokenType.value);
        return "Bearer "+Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSignInKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAccessToken(String email){
        return generateToken(email, TokenType.ACCESS_TOKEN, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String generateRefreshToken(String email){
        return generateToken(email, TokenType.REFRESH_TOKEN, REFRESH_TOKEN_EXPIRE_TIME);
    }
}
