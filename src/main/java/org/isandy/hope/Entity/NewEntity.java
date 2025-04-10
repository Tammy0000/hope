package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "new_entity")
@Accessors(chain = true)
public class NewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 描述
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "created_at", nullable = false)
    private String createdAt;

    /**
     * 更新时间
     */
    @Column(name = "updated_at", nullable = false)
    private String updatedAt;
}