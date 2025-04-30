package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/30 下午3:00
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "hope_project_link_account")
public class HopeProjectLinkAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联账号类型ID
     */
    @Column(name = "account_type_id")
    private Long accountTypeID;

    /**
     * 关联账号描述
     */
    @Column(name = "account_description")
    private String accountDescription;

    /**
     * 关联账号
     */
    @Column(name = "project_account_link")
    private String projectAccountLink;

    /**
     * 关联账户密码
     */
    @Column(name = "project_account_password")
    private String password;

    /**
     * 账号2FA验证地址
     */
    @Column(name = "two_factor_auth_url")
    private String twoFactorAuthUrl;

    /**
     * 账号2FA验证密钥
     */
    @Column(name = "two_factor_auth_secret")
    private String twoFactorAuthSecret;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 邮箱密码
     */
    @Column(name = "email_password")
    private String emailPassword;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time", columnDefinition = "timestamp(0)")
    private LocalDateTime updateTime;

    /**
     * 是否支持Cookie登录
     */
    @Column(name = "is_cookie_login")
    private Boolean isCookieLogin;

    /**
     * CookiesValue
     */
    @Column(name = "cookies_value", columnDefinition = "TEXT")
    private String cookies;
}
