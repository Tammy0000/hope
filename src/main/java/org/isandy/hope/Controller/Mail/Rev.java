package org.isandy.hope.Controller.Mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Service.MailPitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tammy
 * @date 2025/5/10 上午11:04
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class Rev {

    private final MailPitService mailPitService;

    @PostMapping("/mailpit/webhook")
    public void webhook(@RequestBody String body) {
        mailPitService.saveMailSummary(body);
    }
}
