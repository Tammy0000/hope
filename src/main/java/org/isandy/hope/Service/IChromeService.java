package org.isandy.hope.Service;

import java.net.MalformedURLException;

public interface IChromeService {
    default void start() throws InterruptedException, MalformedURLException {}
}
