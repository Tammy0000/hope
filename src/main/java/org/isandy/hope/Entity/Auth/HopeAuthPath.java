package org.isandy.hope.Entity.Auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/5/10 下午4:13
 */
@Getter
@Setter
@Table(name = "hope_auth_path")
@Accessors(chain = true)
@Entity
public class HopeAuthPath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "auth")
    private boolean auth;

    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;
}
