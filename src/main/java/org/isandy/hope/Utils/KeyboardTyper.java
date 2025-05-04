package org.isandy.hope.Utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyboardTyper {
    public static void type(Robot robot,String text) throws AWTException {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }
    }

    /**
     * 输入英文字符串（不支持中文）
     */
    public static void types(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            typeChar(robot, c);
        }
    }

    /**
     * 通过剪贴板粘贴任意文本（推荐用于中文）
     */
    public static void paste(Robot robot,String text) {
        // 将文本复制到剪贴板
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // 模拟 Ctrl + V 粘贴
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * 输入一个字符
     */
    private static void typeChar(Robot robot, char c) {
        boolean upper = Character.isUpperCase(c);
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

        if (keyCode == KeyEvent.VK_UNDEFINED) {
            System.out.println("❌ 无法输入字符: " + c);
            return;
        }

        // 特殊处理：大写、部分符号需 SHIFT
        if (needsShift(c)) robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
        if (needsShift(c)) robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    /**
     * 判断是否需要按 SHIFT（大写或特殊符号）
     */
    private static boolean needsShift(char c) {
        return Character.isUpperCase(c) || "~!@#$%^&*()_+{}|:\"<>?".indexOf(c) >= 0;
    }
}
