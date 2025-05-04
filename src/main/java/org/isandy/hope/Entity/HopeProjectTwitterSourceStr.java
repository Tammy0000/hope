package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "hope_project_twitter_source_str")
@Entity
@Getter
@Setter
@Accessors(chain = true)
/**
 * @author iSandy
 * @description: 用于保存twitter的source字符串(购买下来的字符串)
 */
public class HopeProjectTwitterSourceStr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 原始字符串
     */
    @Column(name = "source_str", columnDefinition = "TEXT")
    String SourceStr;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    /**
     * 是否已使用
     */
    @Column(name = "is_use")
    private Boolean isUse;

    /**
     * 持有者用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 购买时间
     */
    @Column(name = "buy_time", columnDefinition = "timestamp(0)")
    private LocalDateTime buyTime;

    /**
     * 购买价格
     */
    @Column(name = "buy_price")
    private BigDecimal buyPrice;

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
}
