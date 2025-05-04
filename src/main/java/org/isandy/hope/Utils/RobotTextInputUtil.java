package org.isandy.hope.Utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class RobotTextInputUtil {

    private static Robot robot;

    // 特殊字符映射表（需要配合Shift键的字符）
    private static final Map<Character, Integer> shiftCharMap = new HashMap<>();
    // 普通特殊字符映射表
    private static final Map<Character, Integer> normalSpecialCharMap = new HashMap<>();

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("初始化Robot失败", e);
        }

        // 初始化需要Shift键的特殊字符映射
        shiftCharMap.put('!', KeyEvent.VK_1);
        shiftCharMap.put('@', KeyEvent.VK_2);
        shiftCharMap.put('#', KeyEvent.VK_3);
        shiftCharMap.put('$', KeyEvent.VK_4);
        shiftCharMap.put('%', KeyEvent.VK_5);
        shiftCharMap.put('^', KeyEvent.VK_6);
        shiftCharMap.put('&', KeyEvent.VK_7);
        shiftCharMap.put('*', KeyEvent.VK_8);
        shiftCharMap.put('(', KeyEvent.VK_9);
        shiftCharMap.put(')', KeyEvent.VK_0);
        shiftCharMap.put('_', KeyEvent.VK_MINUS);
        shiftCharMap.put('+', KeyEvent.VK_EQUALS);
        shiftCharMap.put('{', KeyEvent.VK_OPEN_BRACKET);
        shiftCharMap.put('}', KeyEvent.VK_CLOSE_BRACKET);
        shiftCharMap.put('|', KeyEvent.VK_BACK_SLASH);
        shiftCharMap.put(':', KeyEvent.VK_SEMICOLON);
        shiftCharMap.put('"', KeyEvent.VK_QUOTE);
        shiftCharMap.put('<', KeyEvent.VK_COMMA);
        shiftCharMap.put('>', KeyEvent.VK_PERIOD);
        shiftCharMap.put('?', KeyEvent.VK_SLASH);
        shiftCharMap.put('~', KeyEvent.VK_BACK_QUOTE);

        // 初始化不需要Shift键的特殊字符映射
        normalSpecialCharMap.put('-', KeyEvent.VK_MINUS);
        normalSpecialCharMap.put('=', KeyEvent.VK_EQUALS);
        normalSpecialCharMap.put('[', KeyEvent.VK_OPEN_BRACKET);
        normalSpecialCharMap.put(']', KeyEvent.VK_CLOSE_BRACKET);
        normalSpecialCharMap.put('\\', KeyEvent.VK_BACK_SLASH);
        normalSpecialCharMap.put(';', KeyEvent.VK_SEMICOLON);
        normalSpecialCharMap.put('\'', KeyEvent.VK_QUOTE);
        normalSpecialCharMap.put(',', KeyEvent.VK_COMMA);
        normalSpecialCharMap.put('.', KeyEvent.VK_PERIOD);
        normalSpecialCharMap.put('/', KeyEvent.VK_SLASH);
        normalSpecialCharMap.put('`', KeyEvent.VK_BACK_QUOTE);
        normalSpecialCharMap.put(' ', KeyEvent.VK_SPACE);
    }

    /**
     * 输入字符串（支持英文、数字、特殊字符和中文）
     * @param text 要输入的字符串
     * @param delay 每个字符输入后的延迟时间（毫秒）
     */
    public static void inputString(String text, int delay) {
        for (char c : text.toCharArray()) {
            if (isChinese(c)) {
                inputChinese(c);
            } else {
                inputAsciiChar(c);
            }
            robot.delay(delay);
        }
    }

    /**
     * 输入ASCII字符（英文、数字、特殊字符）
     * @param c 要输入的字符
     */
    private static void inputAsciiChar(char c) {
        // 处理大写字母
        if (Character.isUpperCase(c)) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            pressAndReleaseKey(Character.toLowerCase(c));
            robot.keyRelease(KeyEvent.VK_SHIFT);
            return;
        }

        // 处理需要Shift的特殊字符（如@、#等）
        if (shiftCharMap.containsKey(c)) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            pressAndReleaseKey(shiftCharMap.get(c));
            robot.keyRelease(KeyEvent.VK_SHIFT);
            return;
        }

        // 处理不需要Shift的特殊字符
        if (normalSpecialCharMap.containsKey(c)) {
            pressAndReleaseKey(normalSpecialCharMap.get(c));
            return;
        }

        // 处理普通字符
        pressAndReleaseKey(c);
    }

    /**
     * 按下并释放一个键
     * @param keyCode 键码
     */
    private static void pressAndReleaseKey(int keyCode) {
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    /**
     * 按下并释放一个键
     * @param c 字符
     */
    private static void pressAndReleaseKey(char c) {
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
        if (keyCode != KeyEvent.VK_UNDEFINED) {
            pressAndReleaseKey(keyCode);
        }
    }

    /**
     * 输入中文字符（使用剪贴板方式）
     * @param c 中文字符
     */
    private static void inputChinese(char c) {
        StringSelection stringSelection = new StringSelection(String.valueOf(c));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        // 使用Ctrl+V粘贴
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * 判断字符是否为中文字符
     * @param c 字符
     * @return 是否为中文字符
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     * 模拟回车键
     */
    public static void pressEnter() {
        pressAndReleaseKey(KeyEvent.VK_ENTER);
    }

    /**
     * 模拟Tab键
     */
    public static void pressTab() {
        pressAndReleaseKey(KeyEvent.VK_TAB);
    }

    /**
     * 模拟退格键
     */
    public static void pressBackspace() {
        pressAndReleaseKey(KeyEvent.VK_BACK_SPACE);
    }

    /**
     * 模拟删除键
     */
    public static void pressDelete() {
        pressAndReleaseKey(KeyEvent.VK_DELETE);
    }
}
