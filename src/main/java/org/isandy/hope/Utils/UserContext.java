package org.isandy.hope.Utils;

/**
 * @author Tammy
 * @date 2025/5/10 下午4:01
 */
public class UserContext {
    private static final ThreadLocal<Long> UserId = new ThreadLocal<>();

    public static Long getUserId() {
        return UserId.get();
    }

    public static void setUserId(Long userId) {
        UserId.set(userId);
    }

    public static void clear() {
        UserId.remove();
    }
}
