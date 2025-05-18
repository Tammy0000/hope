package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "hope_project_virtual_browser")
public class HopeProjectVirtualBrowser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  创建时间
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    LocalDateTime createTime;

    // 持有人
    @Column(name = "user_id")
    private Long userId;

    // 这里是指的是绑定的主机，具有唯一性
    @Column(name = "bind_host")
    private String bindHost;

    @Column(name = "api_key")
    private String apiKey;

    //VirtualBrowser的登录账号
    @Column(name = "login_account")
    private String loginAccount;

    //VirtualBrowser的登录密码
    @Column(name = "login_password")
    private String loginPassword;

    @OneToMany(mappedBy = "hopeProjectVirtualBrowser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<HopeProjectVirtualBrowserLinkAccount> hopeProjectVirtualBrowserLinkAccounts;
}
