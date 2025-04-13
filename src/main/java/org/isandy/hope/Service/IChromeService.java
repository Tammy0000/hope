package org.isandy.hope.Service;

import org.springframework.scheduling.annotation.Async;

public interface IChromeService {
    @Async
    default void start() {}
}
