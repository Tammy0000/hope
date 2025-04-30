package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/30 下午3:59
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "hope_project_account_points")
//todo 项目账号积分
public class HopeProjectAccountPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 项目账号
     */
    @Column(name = "project_account")
    private String projectAccount;

    /**
     * 项目账号当前积分
     */
    @Column(name = "project_account_points")
    private Long projectAccountPoints;

    /**
     * 项目账号积分更新时间
     */
    @Column(name = "project_account_points_update_time", columnDefinition = "timestamp(0)")
    private LocalDateTime projectAccountPointsUpdateTime;
}
