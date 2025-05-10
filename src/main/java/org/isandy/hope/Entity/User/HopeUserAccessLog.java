package org.isandy.hope.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/5/10 下午2:09
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "hope_user_access_log")
@Entity
public class HopeUserAccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ip")
    private String ip;

    //访问路径
    @Column(name = "path")
    private String path;

    //访问时间
    @Column(name = "access_time", columnDefinition = "timestamp(0)")
    private LocalDateTime accessTime;
}
