package org.isandy.hope.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/4/28 上午8:52
 * @description: 助记词生成表
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "hope_user_security_mnemonic")
public class HopeUserSecurityMnemonic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 助记词
    @Column(name = "mnemonic", columnDefinition = "TEXT")
    private String mnemonic;

    // 助记词描述
    @Column(name = "mnemonic_description")
    private String description;

    // 是否已使用
    @Column(name = "is_used")
    private Boolean isUsed;

    // 创建时间
    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    // 使用时间
    @Column(name = "used_time")
    private LocalDateTime usedTime;

    //创建人
    @Column(name = "create_user")
    private String createUser;
}
