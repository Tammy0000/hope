package org.isandy.hope.Config.WebFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tammy
 * @date 2025/4/24 上午8:47
 */
@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final FilterServer filterServers;

    @Bean
    public FilterRegistrationBean<FilterServer> filterService() {
        FilterRegistrationBean<FilterServer> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filterServers);
        registrationBean.addUrlPatterns("/*");  // 可以指定需要拦截的路径
        return registrationBean;
    }
}
