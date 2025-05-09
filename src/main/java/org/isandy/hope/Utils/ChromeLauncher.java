package org.isandy.hope.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChromeLauncher {
    // Chrome 安装路径（可自行修改为 Edge 或其他浏览器）
    private static final String CHROME_PATH = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

    // 指定默认源文件夹路径
    private static final String DEFAULT_SOURCE_DIR = "C:\\tmp\\sourceDir";

    /**
     * 启动 Chrome 并传入远程调试端口、用户数据目录和打开的网址
     *
     * @param port        远程调试端口号（如 9222）
     * @param userDataDir 用户数据目录路径（如 D:\chrome-profile\test1）
     * @param url         启动时要打开的网页（如 <a href="https://example.com">...</a>）
     */
    public static void launch(int port, String userDataDir, String url) {
        try {
            File targetDir = new File(userDataDir);

            // 如果目标目录不存在，则创建并从默认路径复制内容
            if (!targetDir.exists()) {
                targetDir.mkdirs(); // 创建目标文件夹（包含父级）
                copyFromDefaultSource(targetDir);
            }

            List<String> command = new ArrayList<>();
            command.add(CHROME_PATH);
            command.add("--remote-debugging-port=" + port);
            command.add("--user-data-dir=" + userDataDir);
            command.add(url); // 最后是打开的网页地址

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.directory(targetDir); // 设置工作目录（可选）
            builder.start();

            System.out.println("✅ Chrome 已启动，监听端口：" + port);
        } catch (IOException e) {
            System.err.println("❌ 启动失败：" + e.getMessage());
        }
    }

    /**
     * 将默认源路径下的所有内容复制到目标文件夹中
     *
     * @param targetDir 目标文件夹
     * @throws IOException
     */
    private static void copyFromDefaultSource(File targetDir) throws IOException {
        File sourceDir = new File(DEFAULT_SOURCE_DIR);
        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            throw new IOException("源路径不存在或不是一个目录: " + DEFAULT_SOURCE_DIR);
        }

        for (File file : Objects.requireNonNull(sourceDir.listFiles())) {
            File destFile = new File(targetDir, file.getName());
            if (file.isDirectory()) {
                copyFolder(file, destFile); // 复制整个子目录
            } else {
                java.nio.file.Files.copy(file.toPath(), destFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
        }

        System.out.println("✅ 内容已从 " + sourceDir.getAbsolutePath() + " 复制到 " + targetDir.getAbsolutePath());
    }

    /**
     * 递归复制整个文件夹及其内容
     *
     * @param source 源文件夹
     * @param dest   目标文件夹
     * @throws IOException
     */
    private static void copyFolder(File source, File dest) throws IOException {
        if (!dest.exists()) {
            dest.mkdirs();
        }

        for (File file : source.listFiles()) {
            File destFile = new File(dest, file.getName());
            if (file.isDirectory()) {
                copyFolder(file, destFile);
            } else {
                java.nio.file.Files.copy(file.toPath(), destFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
        }

    }
}