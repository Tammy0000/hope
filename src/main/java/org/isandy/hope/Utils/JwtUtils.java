package org.isandy.hope.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Tammy
 * @date 2025/4/28 下午4:22
 */
public class JwtUtils {
    // 签名密钥（建议替换为配置文件读取，增强安全性）
    private static final String SECRET = "YR45Ec7pecLnDhqeWphT";

    // 使用 HMAC256 算法进行签名
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    // 自定义的用户名字段名
    private static final String CLAIM_USERNAME = "hopeProject";

    /**
     * 生成一个默认有效期为 30 天的 Token
     *
     * @param username 要嵌入 token 的用户名
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(String username) {
        return generateToken(username, 30);
    }

    /**
     * 生成指定有效天数的 Token
     *
     * @param username  要嵌入 token 的用户名
     * @param daysValid 有效期（以天为单位）
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(String username, int daysValid) {
        Instant now = Instant.now(); // 当前时间
        Instant expiresAt = now.plusSeconds(daysValid * 86400L); // 过期时间 = 当前时间 + N 天

        return JWT.create()
                .withClaim(CLAIM_USERNAME, username)   // 添加用户名声明
                .withIssuedAt(Date.from(now))          // 签发时间
                .withExpiresAt(Date.from(expiresAt))   // 过期时间
                .sign(ALGORITHM);                      // 使用签名算法生成 Token
    }

    /**
     * 从 Token 中提取用户名
     *
     * @param token 待解析的 JWT 字符串
     * @return 如果 token 有效，返回用户名；无效或过期返回 null
     */
    public static String extractUsername(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).build();
            DecodedJWT decoded = verifier.verify(token); // 验证并解析 Token
            return decoded.getClaim(CLAIM_USERNAME).asString(); // 获取用户名
        } catch (JWTVerificationException e) {
            // 无效 Token 或已过期
            return null;
        }
    }

    /**
     * 提取 Token 的过期时间
     *
     * @param token 待解析的 JWT 字符串
     * @return 如果 token 有效，返回 LocalDateTime 类型的过期时间；无效返回 null
     */
    public static LocalDateTime extractExpiration(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).build();
            DecodedJWT decoded = verifier.verify(token); // 验证并解析 Token
            Date expires = decoded.getExpiresAt(); // 获取过期时间
            if (expires != null) {
                return LocalDateTime.ofInstant(expires.toInstant(), ZoneId.systemDefault());
            }
        } catch (JWTVerificationException e) {
            // 无效 Token 或已过期
            return null;
        }
        return null;
    }
}
