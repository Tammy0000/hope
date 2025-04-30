package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @description: 项目账户日志
 * @author Tammy
 * @date 2025/4/30 下午4:28
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "hope_project_account_log")
public class HopeProjectAccountLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 项目账户
     */
    @Column(name = "project_account")
    private String projectAccount;

    /**
     * 是否成功
     */
    @Column(name = "is_success")
    private Boolean isSuccess;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    /**
     * 截图路径
     */
    @Column(name = "screenshot_path")
    private String screenshotPath;
}
