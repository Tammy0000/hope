package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/30 上午11:23
 */
@Table(name = "hope_project")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class HopeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 项目描述
     */
    @Column(name = "project_description", columnDefinition = "TEXT")
    private String projectDescription;

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
     * 项目状态 0 未开始 1 进行中 2 已完成
     */
    @Column(name = "project_status")
    private int projectStatus;

    /**
     * 项目类型
     */
    @Column(name = "project_type")
    private int projectType;

    /**
     * 项目绑定钱包类型
     */
    @Column(name = "wallet_type")
    private int walletType;

    /**
     * 项目地址
     */
    @Column(name = "project_url")
    private String projectUrl;

    /**
     * 项目账号数量
     */
    @Column(name = "account_number")
    private int accountNumber;

    /**
     * 项目总投资
     */
    @Column(name = "total_investment")
    private BigDecimal totalInvestment;
}
