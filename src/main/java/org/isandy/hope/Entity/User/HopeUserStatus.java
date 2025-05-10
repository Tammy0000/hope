package org.isandy.hope.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/5/10 下午2:03
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "hope_user_status")
@Entity
public class HopeUserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户ID
    private Long userId;

    // 是否封禁
    private boolean isBan;

    // 封禁原因
    private String banReason;

    // 封禁起始时间
    private LocalDateTime banStartTime;

    // 封禁结束时间
    private LocalDateTime banEndTime;

    //操作人用户ID
    private Long operatorUserId;
}
