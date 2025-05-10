package org.isandy.hope.Utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.isandy.hope.Entity.Project.HopeProjectMailSummary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Tammy
 * @date 2025/5/10 上午10:08
 */
public class MailJsonParser {
    public static HopeProjectMailSummary parse(String jsonStr) {
        JSONObject obj = JSON.parseObject(jsonStr);

        // From
        JSONObject from = obj.getJSONObject("From");
        String fromName = from.getString("Name");
        String fromAddress = from.getString("Address");

        // To
        JSONArray toArray = obj.getJSONArray("To");
        String toName = "";
        String toAddress = "";
        if (toArray != null && !toArray.isEmpty()) {
            JSONObject to = toArray.getJSONObject(0);
            toName = to.getString("Name");
            toAddress = to.getString("Address");
        }

        // Subject
        String subject = obj.getString("Subject");

        // Created
        String createdStr = obj.getString("Created");
        LocalDateTime created = LocalDateTime.parse(createdStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // Snippet
        String snippet = obj.getString("Snippet");

        // 封装实体
        HopeProjectMailSummary summary = new HopeProjectMailSummary();
        summary.setFromName(fromName);
        summary.setFromAddress(fromAddress);
        summary.setToName(toName);
        summary.setToAddress(toAddress);
        summary.setSubject(subject);
        summary.setCreated(created);
        summary.setSnippet(snippet);
        summary.setSourceStr(jsonStr);

        return summary;
    }
}
