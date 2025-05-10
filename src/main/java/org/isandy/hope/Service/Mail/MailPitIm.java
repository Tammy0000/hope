package org.isandy.hope.Service.Mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Dao.HopeProjectMailSummaryRepository;
import org.isandy.hope.Entity.Project.HopeProjectMailSummary;
import org.isandy.hope.Service.MailPitService;
import org.isandy.hope.Utils.MailJsonParser;
import org.springframework.stereotype.Service;

/**
 * @author Tammy
 * @date 2025/5/10 上午10:45
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MailPitIm implements MailPitService {

    private final HopeProjectMailSummaryRepository hopeProjectMailSummaryRepository;

    @Override
    public HopeProjectMailSummary covertMailSummary(String SourceStr) {
        return MailJsonParser.parse(SourceStr);
    }

    @Override
    public void saveMailSummary(String SourceStr) {
        HopeProjectMailSummary hopeProjectMailSummary;
        try {
            hopeProjectMailSummary = covertMailSummary(SourceStr);
        } catch (Exception e) {
            log.error("saveMailSummary error: {}", e.getMessage());
            return;
        }
        hopeProjectMailSummaryRepository.save(hopeProjectMailSummary);
    }
}
