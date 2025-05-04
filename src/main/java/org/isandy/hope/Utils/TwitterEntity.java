package org.isandy.hope.Utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TwitterEntity {
    /**
     * twitter账号
     */
    private String twitterAccount;

    /**
     * twitter密码
     */
    private String twitterSourcePassword;

    /**
     * 邮箱账号
     */
    private String email;

    /**
     * 邮箱密码
     */
    private String emailSourcePassword;

    /**
     * 2FA 密码
     */
    private String twitterSource2faPassword;
}
