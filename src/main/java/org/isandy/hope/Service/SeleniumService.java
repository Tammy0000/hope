package org.isandy.hope.Service;

import java.awt.*;
import java.net.MalformedURLException;

public interface SeleniumService {
    /**
     * 用于启动selenium服务
     */
    default void start() throws InterruptedException, MalformedURLException {}

    /**
     * 批量修改推特密码以及2fa密码
     */
    default void updateTwitterPassword(Long UserId) throws Exception {}


    default void TestAdsBrowser() throws InterruptedException {}

    default void loginTwitter(Long UserId) {}
}
