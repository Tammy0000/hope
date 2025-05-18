package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Table(name = "hope_project_twitter")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class HopeProjectTwitter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 推特账号
     */
    @Column(name = "twitter_account")
    private String twitterAccount;

    /**
     * 推特原始密碼
     */
    @Column(name = "twitter_source_password")
    private String twitterSourcePassword;

    /**
     * 推特新密码
     */
    @Column(name = "twitter_new_password")
    private String twitterNewPassword;

    /**
     * 推特更新密码时间
     */
    @Column(name = "twitter_update_password_time", columnDefinition = "timestamp(0)")
    private LocalDateTime twitterUpdatePasswordTime;

    /**
     * 推特原始2FA密碼
     */
    @Column(name = "twitter_source_2fa_password")
    private String twitterSource2faPassword;

    /**
     * 推特新2FA密碼
     */
    @Column(name = "twitter_new_2fa_password")
    private String twitterNew2faPassword;

    /**
     * 推特更新2FA密码时间
     */
    @Column(name = "twitter_update_2fa_password_time", columnDefinition = "timestamp(0)")
    private LocalDateTime twitterUpdate2faPasswordTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    /**
     * 绑定邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 邮箱原始密码
     */
    @Column(name = "email_source_password")
    private String emailSourcePassword;

    /**
     * 邮箱新密码
     */
    @Column(name = "email_new_password")
    private String emailNewPassword;

    /**
     * 邮箱更新密码时间
     */
    @Column(name = "email_update_password_time", columnDefinition = "timestamp(0)")
    private LocalDateTime emailUpdatePasswordTime;

    /**
     * 持有者用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 推特登录网站
     */
    @Column(name = "twitter_login_website")
    private String twitterLoginWebsite;

    /**
     * 邮箱登录网站
     */
    @Column(name = "email_login_website")
    private String emailLoginWebsite;

    /**
     * 2fa转换网站
     */
    @Column(name = "two_fa_conversion_website")
    private String twoFaConversionWebsite;

    /**
     * 是否修改密码
     */
    @Column(name = "is_edit_password")
    private Boolean isEditPassword;

    /**
     * 是否需要2fa
     */
    @Column(name = "is_need_2fa")
    private Boolean isNeed2fa;
}
