package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "hope_selenium_config_User-Agent")
@Getter
@Setter
@Accessors(chain = true)
public class SeleniumUserAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "os_name")
    private String osName;

    @Column(name = "os_id")
    private Long osId;

    @Lob
    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;

    @Column(name = "web_browser")
    private String webBrowser;

    @Column(name = "web_browser_id")
    private Long webBrowserId;
}
