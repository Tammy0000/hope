package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/30 下午4:11
 * @description: 项目收支情况
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "hope_project_cash_flow")
public class HopeProjectCashFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 收支类型 1 收入 2 支出
     */
    @Column(name = "cash_flow_type")
    private Integer cashFlowType;

    /**
     * 收支金额
     */
    @Column(name = "cash_flow_amount")
    private BigDecimal cashFlowAmount;

    /**
     * 收支时间
     */
    @Column(name = "cash_flow_time", columnDefinition = "timestamp(0)")
    private LocalDateTime cashFlowTime;

    /**
     * 收支备注
     */
    @Column(name = "cash_flow_remark", columnDefinition = "TEXT")
    private String cashFlowRemark;

    /**
     * 当前余额
     */
    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    /**
     * 操作用户
     */
    @Column(name = "user_id")
    private Long userId;
}
