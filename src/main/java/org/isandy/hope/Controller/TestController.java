package org.isandy.hope.Controller;

import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Utils.SaResult;
import org.isandy.hope.Utils.SvgCaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tammy
 * @date 2025/4/30 上午10:50
 */
@RestController
@Slf4j
public class TestController {
    @GetMapping("/test")
    public SaResult test() {
        return SaResult.ok().setMsg(SvgCaptchaUtil.generateDefaultSvgCaptcha());
    }
}
