package org.isandy.hope.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChromeLauncher {
    // Chrome 安装路径（可自行修改为 Edge 或其他浏览器）
    private static final String CHROME_PATH = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

    /**
     * 启动 Chrome 并传入远程调试端口、用户数据目录和打开的网址
     * @param port 远程调试端口号（如 9222）
     * @param userDataDir 用户数据目录路径（如 D:\chrome-profile\test1）
     * @param url 启动时要打开的网页（如 https://example.com）
     */
    public static void launch(int port, String userDataDir, String url) {
        try {
            List<String> command = new ArrayList<>();
            command.add(CHROME_PATH);
            command.add("--remote-debugging-port=" + port);
            command.add("--user-data-dir=" + userDataDir);
            command.add(url); // 最后是打开的网页地址

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.directory(new File(userDataDir)); // 设置工作目录（可选）
            builder.start();

            System.out.println("✅ Chrome 已启动，监听端口：" + port);
        } catch (IOException e) {
            System.err.println("❌ 启动失败：" + e.getMessage());
        }
    }
}
