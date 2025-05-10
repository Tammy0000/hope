package org.isandy.hope.Entity.Project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Tammy
 * @date 2025/5/10 上午10:08
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "hope_project_mail_summary")
@Entity
public class HopeProjectMailSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  发件人名称
    @Column(name = "from_name")
    private String fromName;

    //  发件人邮箱
    @Column(name = "from_address")
    private String fromAddress;

    //  收件人名称
    @Column(name = "to_name")
    private String toName;

    //  收件人邮箱
    @Column(name = "to_address")
    private String toAddress;

    // 邮件主题
    @Column(name = "subject")
    private String subject;

    @Column(name = "created", columnDefinition = "timestamp(0)")
    private LocalDateTime created;

    // 邮件内容
    @Column(columnDefinition = "text", name = "snippet")
    private String snippet;

    //邮件原始字符串
    @Column(columnDefinition = "text",name = "source_str")
    private String sourceStr;
}
