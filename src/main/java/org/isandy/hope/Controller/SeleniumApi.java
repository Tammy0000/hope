package org.isandy.hope.Controller;

import lombok.RequiredArgsConstructor;
import org.isandy.hope.Service.IChromeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SeleniumApi {
    private final IChromeService chromeService;

    @GetMapping("/selenium")
    public void start() {
        chromeService.start();
    }
}
