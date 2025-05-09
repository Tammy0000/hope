package org.isandy.hope.Utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HttpUtils {
    private static final HttpClient DEFAULT_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    // 同步 GET 请求
    public static HttpResponse<String> get(String url) throws Exception {
        return get(url, null, null);
    }

    public static HttpResponse<String> get(String url, Map<String, String> headers) throws Exception {
        return get(url, headers, null);
    }

    public static HttpResponse<String> get(String url, Map<String, String> headers, Duration timeout) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        if (headers != null) {
            headers.forEach(builder::header);
        }

        if (timeout != null) {
            builder.timeout(timeout);
        }

        return DEFAULT_CLIENT.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    // 异步 GET 请求
    public static CompletableFuture<HttpResponse<String>> getAsync(String url) {
        return getAsync(url, null, null);
    }

    public static CompletableFuture<HttpResponse<String>> getAsync(String url, Map<String, String> headers) {
        return getAsync(url, headers, null);
    }

    public static CompletableFuture<HttpResponse<String>> getAsync(String url, Map<String, String> headers, Duration timeout) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        if (headers != null) {
            headers.forEach(builder::header);
        }

        if (timeout != null) {
            builder.timeout(timeout);
        }

        return DEFAULT_CLIENT.sendAsync(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    // 同步 POST 请求
    public static HttpResponse<String> post(String url, String body) throws Exception {
        return post(url, body, null, null);
    }

    public static HttpResponse<String> post(String url, String body, Map<String, String> headers) throws Exception {
        return post(url, body, headers, null);
    }

    public static HttpResponse<String> post(String url, String body, Map<String, String> headers, Duration timeout) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body));

        if (headers != null) {
            headers.forEach(builder::header);
        } else {
            // 默认设置 Content-Type 为 application/json
            builder.header("Content-Type", "application/json");
        }

        if (timeout != null) {
            builder.timeout(timeout);
        }

        return DEFAULT_CLIENT.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    // 异步 POST 请求
    public static CompletableFuture<HttpResponse<String>> postAsync(String url, String body) {
        return postAsync(url, body, null, null);
    }

    public static CompletableFuture<HttpResponse<String>> postAsync(String url, String body, Map<String, String> headers) {
        return postAsync(url, body, headers, null);
    }

    public static CompletableFuture<HttpResponse<String>> postAsync(String url, String body, Map<String, String> headers, Duration timeout) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body));

        if (headers != null) {
            headers.forEach(builder::header);
        } else {
            builder.header("Content-Type", "application/json");
        }

        if (timeout != null) {
            builder.timeout(timeout);
        }

        return DEFAULT_CLIENT.sendAsync(builder.build(), HttpResponse.BodyHandlers.ofString());
    }
}
