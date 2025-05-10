package org.isandy.hope.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/30 上午11:18
 */
@Table(name = "hope_user_project")
@Getter
@Setter
@Entity
@Accessors(chain = true)
public class HopeUserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;
}
