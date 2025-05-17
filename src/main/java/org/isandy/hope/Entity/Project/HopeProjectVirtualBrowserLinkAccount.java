package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Table(name = "hope_project_virtual_browser_link_account")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class HopeProjectVirtualBrowserLinkAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //关联账号类型
    @Column(name = "account_type")
    private String accountType;

    //账号
    @Column(name = "account")
    private String account;

    //登录网站
    @Column(name = "login_url", columnDefinition = "TEXT")
    private String loginUrl;

    //关联时间
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    //账号持有人
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "browser_id")
    private HopeProjectVirtualBrowser hopeProjectVirtualBrowser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private HopeProjectAccountType hopeProjectAccountType;
}
