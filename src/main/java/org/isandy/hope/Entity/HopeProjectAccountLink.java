package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Tammy
 * @date 2025/4/30 下午2:25
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "hope_project_account_link")
public class HopeProjectAccountLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联的项目ID
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 项目账号
     */
    @Column(name = "project_account_link")
    private String projectAccountLink;

    /**
     * 关联账号
     */
    @Column(name = "project_account_link_type")
    private Long projectAccountLinkType;
}
