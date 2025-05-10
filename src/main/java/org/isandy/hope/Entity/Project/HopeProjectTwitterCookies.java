package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Table(name = "hope_project_twitter_cookies")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class HopeProjectTwitterCookies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 推特账号
     */
    @Column(name = "twitter_account")
    private String twitterAccount;

    /**
     * 推特cookies
     */
    @Column(name = "twitter_cookies", columnDefinition = "TEXT")
    private String twitterCookies;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;
}
