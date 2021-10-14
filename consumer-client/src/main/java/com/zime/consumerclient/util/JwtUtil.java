package com.zime.consumerclient.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String SUBJECT = "topgun"; //签名主题
    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7; //一周有效
    public static final String APPSECRET_KEY = "topgun_secret"; //密钥
    private static final String ROLE_CLAIMS = "role"; //角色权限

    public static String createToken(String username,String role){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ROLE_CLAIMS,role);
        String token = Jwts
                .builder()
                .setClaims(map)
                .claim("username",username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRITION))
                .signWith(SignatureAlgorithm.HS256,APPSECRET_KEY).compact();
        return token;
    }

    public static Claims checkJWT(String token){
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(APPSECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(APPSECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("username").toString();
    }

    public static String getUserRole(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(APPSECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role").toString();
    }

    public static boolean isExpiration(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(APPSECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }

}
