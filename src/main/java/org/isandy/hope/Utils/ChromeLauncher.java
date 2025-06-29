package org.isandy.hope.Utils;

import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class ChromeLauncher {
    // Chrome 安装路径（可自行修改为 Edge 或其他浏览器）
    private static final String CHROME_PATH = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

    // 指定默认源文件夹路径
    private static final String DEFAULT_SOURCE_DIR = "C:\\tmp\\sourceDir";

    private static final int DEFAULT_MiN = 10000;

    private static final int DEFAULT_MAX = 65535;

    /**
     * 启动 Chrome 并传入远程调试端口、用户数据目录和打开的网址
     *
     * @param userDataDir 用户数据目录路径（如 D:\chrome-profile\test1）
     * @param url         启动时要打开的网页（如 <a href="https://example.com">...</a>）
     */
    public static int launch(String userDataDir, String url) {
        try {
            File targetDir = new File(userDataDir);

            // 如果目标目录不存在，则创建并从默认路径复制内容
            if (!targetDir.exists()) {
                targetDir.mkdirs(); // 创建目标文件夹（包含父级）
                copyFromDefaultSource(targetDir);
            }

            int port;

            do {
                port = getRandomIntInRange(DEFAULT_MiN, DEFAULT_MAX);
            } while (!isPortInUse(port));

            List<String> command = new ArrayList<>();
            command.add(CHROME_PATH);
            command.add("--remote-debugging-port=" + port);
            command.add("--user-data-dir=" + userDataDir);
            command.add(url); // 最后是打开的网页地址

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.directory(targetDir); // 设置工作目录（可选）
            builder.start();

            return port;
        } catch (IOException e) {
            return -1;
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

    public static ChromeOptions createChromeOptions(int port) {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:" + port);
        options.setBinary("C:/Program Files/VirtualBrowser/VirtualBrowser/132.0.6834.99/VirtualBrowser.exe");
        return options;
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

    // 判断某个端口是否被占用
    public static boolean isPortInUse(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            return false; // 没抛异常，说明端口可用
        } catch (IOException e) {
            return true; // 抛出异常，说明端口已被占用
        }
    }

    public static int getRandomIntInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("最小值不能大于最大值");
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * 关闭已打开远程调试端口的 Chrome 浏览器
     * @param port Chrome remote-debugging-port，例如 9222
     * @throws Exception 出错时抛出异常
     */
    public static void closeChromeByDebugPort(int port) throws Exception {
        String wsUrl = getWebSocketDebuggerUrl(port);
        if (wsUrl == null) {
            System.err.println("未找到 WebSocket Debugger URL，Chrome 是否使用了 --remote-debugging-port=" + port + " 启动？");
            return;
        }

        CountDownLatch latch = new CountDownLatch(1);
        HttpClient client = HttpClient.newHttpClient();

        client.newWebSocketBuilder()
                .buildAsync(URI.create(wsUrl), new WebSocket.Listener() {
                    @Override
                    public void onOpen(WebSocket webSocket) {
                        System.out.println("连接成功，发送 Browser.close 命令");
                        String command = "{ \"id\": 1, \"method\": \"Browser.close\" }";
                        webSocket.sendText(command, true);
                        latch.countDown();
                    }

                    @Override
                    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                        System.out.println("收到响应: " + data);
                        return null;
                    }

                    @Override
                    public void onError(WebSocket webSocket, Throwable error) {
                        System.err.println("发生错误: " + error.getMessage());
                    }
                }).join();

        latch.await();
        System.out.println("关闭指令已发送");
    }

    /**
     * 获取 WebSocket 调试 URL（从 http://localhost:port/json 获取）
     * @param port 调试端口号
     * @return WebSocket Debugger URL 或 null
     */
    private static String getWebSocketDebuggerUrl(int port) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:" + port + "/json"))
                .GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        int idx = body.indexOf("ws://");
        if (idx == -1) return null;
        int end = body.indexOf("\"", idx);
        return body.substring(idx, end);
    }
}