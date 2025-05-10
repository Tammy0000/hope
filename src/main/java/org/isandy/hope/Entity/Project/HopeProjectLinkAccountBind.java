package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/30 下午3:27
 */
@Entity
@Table(name = "hope_project_link_account_bind")
@Getter
@Setter
@Accessors(chain = true)
public class HopeProjectLinkAccountBind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Long projectID;

    /**
     * 关联账号
     */
    @Column(name = "project_account_link")
    private String projectAccountLink;

    /**
     * 绑定时间
     */
    @Column(name = "bind_time", columnDefinition = "timestamp(0)")
    private LocalDateTime bindTime;
}
