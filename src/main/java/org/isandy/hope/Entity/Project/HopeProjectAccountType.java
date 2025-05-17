package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "hope_project_account_type")
@Getter
@Setter
@Accessors(chain = true)
public class HopeProjectAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_id")
    private Long TypeId;

    @Column(name = "type_name")
    private String TypeName;

    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "hopeProjectAccountType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<HopeProjectVirtualBrowserLinkAccount> hopeProjectVirtualBrowserLinkAccounts;
}
