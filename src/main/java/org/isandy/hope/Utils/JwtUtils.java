package org.isandy.hope.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author Tammy
 * @date 2025/4/28 下午4:22
 */
public class JwtUtils {
    // 密钥 (实际项目中应使用更复杂的密钥并保密)
    private static final String SECRET_KEY = "mySecretKey";

    // 默认的 JWT有效期: 1小时
    private static final long DEFAULT_EXPIRATION_TIME = 60 * 60 * 1000; // 1小时

    /**
     * 1. 生成JWT并返回Token字符串
     *
     * @param username 用户名或用户ID
     * @param expirationTime 有效期，单位：秒。如果为0或小于等于0，则使用默认有效期（1小时）
     * @return JWT Token
     */
    public static String generateToken(String username, long expirationTime) {
        if (expirationTime <= 0) {
            expirationTime = DEFAULT_EXPIRATION_TIME / 1000;  // 如果传入有效期为0或负数，使用默认1小时
        }

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);  // 使用 HMAC256 算法

        return JWT.create()
                .withSubject(username)  // 设置主体，通常是用户名或者用户ID
                .withIssuedAt(new Date())  // 设置签发时间
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime * 1000))  // 设置过期时间
                .sign(algorithm);  // 使用指定的算法签名
    }

    /**
     * 2. 校验Token是否有效
     *
     * @param token JWT Token
     * @return 如果有效则返回true，否则返回false
     */
    public static boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWT.require(algorithm)
                    .build()
                    .verify(token);  // 验证JWT Token
            return true;
        } catch (TokenExpiredException e) {
            // Token过期
            System.out.println("Token is expired");
        } catch (JWTVerificationException | IllegalArgumentException e) {
            // Token无效
            System.out.println("Invalid token");
        }
        return false;
    }

    /**
     * 3. 获取Token的有效日期，并返回LocalDateTime格式
     *
     * @param token JWT Token
     * @return Token的过期时间（LocalDateTime格式）
     */
    public static LocalDateTime getExpirationDate(String token) {
        try {
            // 解码Token获取过期时间
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            Date expirationDate = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getExpiresAt();  // 获取Token的过期时间

            if (expirationDate != null) {
                // 转换为 LocalDateTime
                return expirationDate.toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime();
            }
        } catch (JWTVerificationException | IllegalArgumentException e) {
            System.out.println("Error while getting expiration date");
        }
        return null;
    }
}
