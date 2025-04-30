package org.isandy.hope.Utils;

import java.util.Random;

/**
 * @author Tammy
 * @date 2025/4/30 上午8:41
 */
public class SvgCaptchaUtil {
    // 可自定义的验证码参数
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_CAPTCHA_LENGTH = 6; // 默认验证码长度
    private static final int DEFAULT_WIDTH = 200; // 默认图像宽度
    private static final int DEFAULT_HEIGHT = 60; // 默认图像高度

    // 生成随机验证码文本
    public static String generateCaptchaText(int length) {
        Random random = new Random();
        StringBuilder captchaText = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHAR_POOL.length());
            captchaText.append(CHAR_POOL.charAt(index));
        }
        return captchaText.toString();
    }

    // 生成 SVG 格式的验证码
    public static String generateSvgCaptcha(String captchaText, int width, int height) {
        StringBuilder svg = new StringBuilder();

        // SVG 文档头部
        svg.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        svg.append("<svg width=\"").append(width).append("\" height=\"").append(height).append("\" xmlns=\"http://www.w3.org/2000/svg\">\n");

        // 背景
        svg.append("<rect width=\"100%\" height=\"100%\" fill=\"#f0f0f0\" />\n");

        // 添加验证码文本，随机字体样式和位置
        Random random = new Random();
        for (int i = 0; i < captchaText.length(); i++) {
            int fontSize = random.nextInt(10) + 20;  // 随机字体大小
            int x = 30 + (i * 30);  // 文字水平位置
            int y = random.nextInt(15) + 30;  // 文字垂直位置
            svg.append("<text x=\"").append(x).append("\" y=\"").append(y).append("\" font-family=\"Arial\" font-size=\"").append(fontSize).append("\" fill=\"#000000\">")
                    .append(captchaText.charAt(i))
                    .append("</text>\n");
        }

        // 添加一些干扰线条
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            svg.append("<line x1=\"").append(x1).append("\" y1=\"").append(y1).append("\" x2=\"").append(x2).append("\" y2=\"").append(y2).append("\" stroke=\"#c0c0c0\" stroke-width=\"2\" />\n");
        }

        // SVG 文档尾部
        svg.append("</svg>");

        return svg.toString();
    }

    // 返回默认长度、宽度和高度的验证码 SVG 字符串
    public static String generateDefaultSvgCaptcha() {
        String captchaText = generateCaptchaText(DEFAULT_CAPTCHA_LENGTH);
        return generateSvgCaptcha(captchaText, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
