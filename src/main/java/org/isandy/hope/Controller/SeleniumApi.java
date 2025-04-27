package org.isandy.hope.Controller;

import lombok.RequiredArgsConstructor;
import org.isandy.hope.Service.IChromeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
public class SeleniumApi {

    private final IChromeService chromeService;

    @GetMapping("/selenium")
    public void start() throws InterruptedException, MalformedURLException {
        for (int i = 0; i < 10; i++) {
            chromeService.start();
        }
    }
}
