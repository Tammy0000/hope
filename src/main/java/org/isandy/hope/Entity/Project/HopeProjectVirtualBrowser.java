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

    //  关联的浏览器id
    @Column(name = "browser_id")
    private Long browserId;

    //  创建时间
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    LocalDateTime createTime;

    // 持有人
    @Column(name = "user_id")
    private Long userId;

    // 浏览器api-key
    @Column(name = "api_key")
    private String apiKey;

    // virtualBrowser 启动地址
    @Column(name = "host")
    private String host;

    @OneToMany(mappedBy = "hopeProjectVirtualBrowser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<HopeProjectVirtualBrowserLinkAccount> hopeProjectVirtualBrowserLinkAccounts;
}
