package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Table(name = "hope_project_virtual_browser_log")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class HopeProjectVirtualBrowserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  浏览器id
    @Column(name = "browser_id")
    private Long browserId;

    // 日志时间
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    // 日志内容
    @Column(name = "log", columnDefinition = "TEXT")
    private String log;

    // 是否成功
    @Column(name = "status")
    private Boolean status;

    // 账号
    @Column(name = "account")
    private String account;

    //关联账号类型
    @Column(name = "account_type")
    private String accountType;
}
