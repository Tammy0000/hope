package org.isandy.hope.Entity.Selenium;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "hope_selenium_browser_cookies")
@Entity
@Getter
@Setter
public class HopeSeleniumBrowserCookie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //账号类型
    @Column(name = "account_type")
    private String accountType;

    //登录网站首页
    @Column(name = "login_url", columnDefinition = "TEXT")
    private String loginUrl;

    //账号
    @Column(name = "account")
    private String account;

    //密码
    @Column(name = "password")
    private String password;
}
