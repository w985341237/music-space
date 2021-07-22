package com.neil.musicspace.utils.jwt;

import com.neil.musicspace.models.entity.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description JwtUtil
 * @Author neil
 * @Date 2021/7/8 18:17
 * @Version 1.0
 **/
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * token 前缀
     */
    private final static String TOKEN_PREFIX = "Bearer ";

    /**
     * 密钥
     */
    private final static String SECRET = "mamba4life";

    /**
     * 有效时间
     */
    private final static long EXPIRE = 1800L;

    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法
     * @param user
     * @return
     */
    public static String createToken(User user, String session_key) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expireDate = new Date(nowMillis + EXPIRE * 1000);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("session_key", session_key);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(signatureAlgorithm, SECRET.getBytes());

        return builder.compact();
    }

    /**
     * 解码JWT
     *
     * @param token
     * @return
     */
    public static Claims parseToekn(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token).getBody();

            return claims;
        } catch (JwtException exception) {
            logger.error(exception.getMessage());
            return null;
        }

    }
}
