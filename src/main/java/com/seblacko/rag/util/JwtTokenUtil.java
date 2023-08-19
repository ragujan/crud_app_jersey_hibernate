package com.seblacko.rag.util;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String ISSUER = "crud_app";
    private static final String SECRET = "Z2N2GDy(U_/q[13?3F8,$QQ+2t6+V2";
    private static final Long TOKEN_LIFE = 5L;
    private static final Long REFRESH_TOKEN_LIFE = 43200L;

    private String generateToken(Map<String,String> claims, Long expiration, String subject){
        Signer signer = HMACSigner.newSHA512Signer(SECRET);

        JWT jwt = new JWT().setIssuer(ISSUER).setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setSubject(subject)
                .setExpiration(ZonedDateTime.now(ZoneOffset.UTC)
                        .plusMinutes(TOKEN_LIFE));
        claims.keySet().forEach(k->{
            if(claims.get(k) != null){
                jwt.addClaim(k,claims.get(k));
            }
        });
        return JWT.getEncoder().encode(jwt,signer);
    }
    public String generateAccessToken(UserDetails userDetails){
        Map<String,String> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getEmail());
        claims.put(CLAIM_KEY_CREATED,new Date().toString());

        return generateToken(claims,TOKEN_LIFE,userDetails.getEmail());
    }
    public Map<String,String> getClaimsFromToken(String token){
        Verifier verifier = HMACVerifier.newVerifier(SECRET);
        JWT jwt = JWT.getDecoder().decode(token,verifier);
        Map<String,Object> jwtAllClaims = jwt.getAllClaims();
        Map<String,String> claims = new HashMap<>();

        if(jwt != null){
            jwtAllClaims.forEach((k,v)->{
                claims.put(k,v.toString());
            });
        }
        return claims;
    }
    public Date getExpiredDateFromToken(String token){
        Verifier verifier = HMACVerifier.newVerifier(SECRET);
        JWT jwt = JWT.getDecoder().decode(token,verifier);
        return new Date(jwt.expiration.toInstant().toEpochMilli() );
    }
    public boolean isTokenExpired(String token){
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date(System.currentTimeMillis()));
    }
    public String getUsernameFromToken(String token){
        Map<String,String> claims = getClaimsFromToken(token);
        return claims.get(CLAIM_KEY_USERNAME);
    }

    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getEmail()) && !isTokenExpired(token);
    }
    public String generateRefreshToken(UserDetails userDetails){
        Map<String,String> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getEmail());
        claims.put(CLAIM_KEY_CREATED,new Date().toString());
        return generateToken(claims,REFRESH_TOKEN_LIFE,userDetails.getEmail());
    }

}
