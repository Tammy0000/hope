package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Tammy
 * @date 2025/4/30 下午2:48
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "hope_project_account_link_type")
public class HopeProjectLinkAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联账号描述
     */
    @Column(name = "account_description")
    private String accountDescription;

    /**
     * 关联账号类型ID
     */
    @Column(name = "account_type_id")
    private Long accountTypeID;
}
