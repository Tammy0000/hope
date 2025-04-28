package org.isandy.hope.Config.WebFilter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Utils.SaResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author Tammy
 * @date 2025/4/24 上午8:42
 */
@WebFilter("/*")
@Slf4j
@Service
@RequiredArgsConstructor
public class FilterServer implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求的URI路径
        String uri = request.getRequestURI();

        // 获取请求参数
        ServletInputStream stream = request.getInputStream();
        byte[] bytes = stream.readAllBytes();
        String datas = new String(bytes, StandardCharsets.UTF_8);
        filterChain.doFilter(request, response);

    }


}
