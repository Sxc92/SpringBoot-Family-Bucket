package com.christ.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author 史偕成
 * @date 2023/08/07 21:11
 **/
@Service
public class JwtService {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F44284472B4B6250645367566B5970";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 根据token 提取 Claim
     *
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(16), userDetails);
    }

    /**
     * 验证token是否有效
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * 验证token是否过期
     *
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 创建token
     *
     * @param extraClaim
     * @param userDetails
     * @return
     */
    public String generateToken(
            Map<String, Object> extraClaim,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaim)
                // 存放用户名称
                .setSubject(userDetails.getUsername())
                // 发布时间
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 过期时间 设置为24小时  一天时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                // 设置 密钥 和加密方式
                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                // 获取一个密钥
                .setSigningKey(getSignIngKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public SecretKey getSignIngKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
