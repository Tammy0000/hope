package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/30 下午2:11
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "hope_project_account")
public class HopeProjectAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所属项目ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 所属用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 项目账号
     */
    @Column(name = "project_account")
    private String projectAccount;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 区块链助记词
     */
    @Column(name = "mnemonic")
    private String mnemonic;

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
}
