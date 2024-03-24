package com.Classroom.Classroom.Security.JWTS;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@AllArgsConstructor
public class JwtUtils {

    private final String jwtsSecret="002aa3e983fabccc78b609ef9aec1d9a27d015a9bb41de0e934e9321a4f52525";

    private final long jwtsExpiration=604800000;

    public String getJwtsToken(Authentication authentication){
        String username= authentication.getName();

        Date issuedAt=new Date();

        Date expiration=new Date(issuedAt.getTime()+jwtsExpiration);

        String token=Jwts.builder()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(getKey())
                .compact();
        return token;
    }

    public  String getUsername(String token){
        String username=Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return username;
    }

    public Boolean isValid(String token){
        Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parse(token);

        return true;
    }

    public Key getKey(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtsSecret)
        );
    }

}
