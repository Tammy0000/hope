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

    //关联账号类型 三件套分别是 tw,dc,tg 推特 dc 小飞机
    @Column(name = "account_type")
    private String accountType;

    //virtual_browser_id 虚拟浏览器id
    @Column(name = "virtual_browser_id")
    private Long virtualBrowserId;

    //账号
    @Column(name = "account")
    private String account;

    //登录网站
    @Column(name = "login_url", columnDefinition = "TEXT")
    private String loginUrl;

    // 是否是项目账号
    @Column(name = "is_project_account")
    private Boolean isProjectAccount;

    //关联时间
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    //账号持有人
    @Column(name = "user_id")
    private Long userId;

    //虚拟浏览器序号ID
    @Column(name = "virtual_browser_index_id")
    private Long virtualBrowserIndexId;

}
