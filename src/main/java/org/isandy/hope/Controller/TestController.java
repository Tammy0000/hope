package org.isandy.hope.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.SeleniumService;
import org.isandy.hope.Utils.SaResult;
import org.isandy.hope.Utils.SvgCaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @author Tammy
 * @date 2025/4/30 上午10:50
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {
    private final SeleniumService seleniumService;
    @GetMapping("/test")
    public SaResult test() throws InterruptedException, AWTException {
        seleniumService.updateTwitterPassword(1L);
        return SaResult.ok();
    }

    @PostMapping("/metric/{id}")
    public String metric(@PathVariable String id) {
        log.info("metric: {}", id);
        return "doubtful but ok";
    }
}
