package com.example.RoleBasedAuthorization.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service

public class JwtService {

    private static final String SECURITY_KEY = "526F6C6520626173656420617574686F72697A6174696F6E";


    public String extractUserName(String token){
         return extractClaim(token,Claims::getSubject);
    }

    public Claims extractAllClaims(String token){

        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] bytesKey= Decoders.BASE64.decode(SECURITY_KEY);
        return Keys.hmacShaKeyFor(bytesKey);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public boolean isValid(UserDetails userDetails,String token){
        String userName=extractUserName(token);
        if(userName.equals(userDetails.getUsername()) && !isExpired(token)){
            return true;
        }
        return false;
    }

    public boolean isExpired(String token){
        return  expirationDate(token).before(new Date(System.currentTimeMillis()));
    }

    public Date expirationDate(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails,Map<String,Object> extraClaims){

       return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }


   public String generateToken(UserDetails userDetails){
        return generateToken(userDetails,new HashMap<>());
   }


}
