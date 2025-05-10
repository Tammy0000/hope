package org.isandy.hope.Utils;

import java.util.Random;

/**
 * @author Tammy
 * @date 2025/5/10 下午1:39
 */
public class RandomLongGenerator {
    private static final Random random = new Random();

    /**
     * 生成指定位数的 Long 类型随机数
     *
     * @param digits 位数（1~18）
     * @return Long 类型随机数
     * @throws IllegalArgumentException 如果位数不合法
     */
    public static long generate(int digits) {
        if (digits < 1 || digits > 18) {
            throw new IllegalArgumentException("位数必须在 1 到 18 之间");
        }

        long min = (long) Math.pow(10, digits - 1);
        long max = (long) Math.pow(10, digits) - 1;

        // 处理溢出边界（最多 18 位）
        if (max < 0) max = Long.MAX_VALUE;

        return min + (long)(random.nextDouble() * (max - min + 1));
    }
}
