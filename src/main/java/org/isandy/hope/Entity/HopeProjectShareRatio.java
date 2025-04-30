package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/30 下午2:04
 */
@Entity
@Table(name = "hope_project_share_ratio")
@Getter
@Setter
@Accessors(chain = true)
public class HopeProjectShareRatio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 分配比例
     */
    @Column(name = "share_ratio")
    private int shareRatio;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time", columnDefinition = "timestamp(0)")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 公共支出
     */
    @Column(name = "public_expense")
    private BigDecimal publicExpense;
}
