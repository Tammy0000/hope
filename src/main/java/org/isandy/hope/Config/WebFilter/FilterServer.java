package org.isandy.hope.Config.WebFilter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeAuthPathRepository;
import org.isandy.hope.Dao.HopeUserRepository;
import org.isandy.hope.Dao.HopeUserStatusRepository;
import org.isandy.hope.Entity.Auth.HopeAuthPath;
import org.isandy.hope.Entity.User.HopeUser;
import org.isandy.hope.Utils.JwtUtils;
import org.isandy.hope.Utils.UserContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private final HopeUserStatusRepository  hopeUserStatusRepository;

    private final HopeUserRepository  hopeUserRepository;

    private final HopeAuthPathRepository hopeAuthPathRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //  获取请求和响应
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //设置好响应头，否则会中文编码会乱码
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 获取请求参数
        byte[] bytes = request.getInputStream().readAllBytes();
        //  包装请求，支持多次读取 body
        CachedBodyHttpServletRequestWrapper wrappedRequest = new CachedBodyHttpServletRequestWrapper(request, bytes);
        // 获取请求的URI路径
        String uri = request.getRequestURI();

        //处理不需要认证的路径
        if (isMatch(uri, false)) {
            filterChain.doFilter(wrappedRequest, response);
            return;
        }

        //获取请求头
        String header = request.getHeader("Authorization");
        String username = JwtUtils.extractUsername(header);
        //检查用户是否可以登录
        if (Objects.isNull(username)) {
            response.getWriter().write("{\"code\":401,\"msg\":\"未登录或者登录已过期!\"}");
            return;
        }
        HopeUser byPhone = hopeUserRepository.findByPhone(username);
        boolean isBan = hopeUserStatusRepository.existsByUserIdAndIsBan(byPhone.getId(), true);
        if (isBan) {
            response.getWriter().write("{\"code\":401,\"msg\":\"用户被封禁!\"}");
            return;
        }
        try {
            UserContext.setUserId(byPhone.getId());
            filterChain.doFilter(wrappedRequest, response);
        } finally {
            UserContext.clear();
        }
    }

    /**
     * 判断是否需要过滤
     * @param URI URI
     * @param isAuth 该路径是否需要认证
     * @return 是否需要过滤
     */
    private boolean isMatch(String URI, boolean isAuth) {
        List<HopeAuthPath> byIsAuth = hopeAuthPathRepository.findByAuth(isAuth);
        for (HopeAuthPath path : byIsAuth) {
            String noAuthPath = path.getPath();
            // 去除结尾的 **
            String substring = noAuthPath.substring(0, noAuthPath.length() - 2);
            if (URI.startsWith(substring)) {
                return true;
            }
        }
        return false;
    }
}
