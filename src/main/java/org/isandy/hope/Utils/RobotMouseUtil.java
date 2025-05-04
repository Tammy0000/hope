package org.isandy.hope.Utils;

import java.awt.*;
import java.awt.event.InputEvent;

public class RobotMouseUtil {
    /**
     * 当前鼠标位置左键单击
     */
    public static void leftClick(Robot robot) {
        click(robot, InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * 当前鼠标位置右键单击
     */
    public static void rightClick(Robot robot) {
        click(robot, InputEvent.BUTTON3_DOWN_MASK);
    }

    /**
     * 当前鼠标位置中键单击
     */
    public static void middleClick(Robot robot) {
        click(robot, InputEvent.BUTTON2_DOWN_MASK);
    }

    /**
     * 当前鼠标位置左键双击
     */
    public static void doubleClick(Robot robot) {
        leftClick(robot);
        robot.delay(100);
        leftClick(robot);
    }

    /**
     * 在指定坐标左键单击
     */
    public static void leftClickAt(Robot robot, int x, int y) {
        clickAt(robot, x, y, InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * 在指定坐标右键单击
     */
    public static void rightClickAt(Robot robot, int x, int y) {
        clickAt(robot, x, y, InputEvent.BUTTON3_DOWN_MASK);
    }

    /**
     * 在指定坐标左键双击
     */
    public static void doubleClickAt(Robot robot, int x, int y) {
        robot.mouseMove(x, y);
        doubleClick(robot);
    }

    /**
     * 在指定坐标使用指定按钮点击
     */
    public static void clickAt(Robot robot, int x, int y, int buttonMask) {
        robot.mouseMove(x, y);
        click(robot, buttonMask);
    }

    /**
     * 按下 → 延迟 → 释放 指定鼠标按钮
     */
    private static void click(Robot robot, int buttonMask) {
        robot.mousePress(buttonMask);
        robot.delay(50);
        robot.mouseRelease(buttonMask);
    }
}
