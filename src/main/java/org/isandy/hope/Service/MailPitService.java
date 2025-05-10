package org.isandy.hope.Service;

import org.isandy.hope.Entity.Project.HopeProjectMailSummary;

/**
 * @author Tammy
 * @date 2025/5/10 上午10:32
 */
public interface MailPitService {
    /**
     * @description: 通过对mailpit邮件内容进行解析，生成邮件实体类
     * @param SourceStr mailpit邮件内容
     * @return HopeProjectMailSummary实体类
     */
    //todo 将mailpit转发过来的邮件内容转换成HopeProjectMailSummary实体类
    default HopeProjectMailSummary covertMailSummary(String SourceStr) {
        return null;
    }

    /**
     * @description: 保存邮件内容到数据库中
     * @param SourceStr mailpit邮件内容
     */
    //todo 保存邮件内容到数据库中
    default void saveMailSummary(String SourceStr) {}
}
