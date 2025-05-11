package org.isandy.hope.Service;

import java.net.MalformedURLException;

public interface TwitterSeleniumService extends SeleniumService{
    @Override
    default void start() throws InterruptedException, MalformedURLException {
        SeleniumService.super.start();
    }

    @Override
    default void updateTwitterPassword(Long UserId) throws Exception {
        SeleniumService.super.updateTwitterPassword(UserId);
    }

    default void launchTwitter() throws InterruptedException {}

    default void TestLoad() throws InterruptedException {}
}
