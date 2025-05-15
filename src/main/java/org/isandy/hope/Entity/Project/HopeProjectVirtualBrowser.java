package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "hope_project_virtual_browser")
public class HopeProjectVirtualBrowser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "browser_id")
    private Long browserId;

    @Column(name = "create_time", columnDefinition = "timestamp(0)")
    LocalDateTime createTime;

    @OneToMany(mappedBy = "hopeProjectVirtualBrowser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<HopeProjectVirtualBrowserLinkAccount> hopeProjectVirtualBrowserLinkAccounts;
}
