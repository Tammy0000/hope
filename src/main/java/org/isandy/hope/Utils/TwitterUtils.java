package org.isandy.hope.Utils;

import lombok.extern.slf4j.Slf4j;
import org.isandy.hope.Entity.HopeProjectTwitter;

/**
 * TwitterUtils
 */
@Slf4j
public class TwitterUtils {
    public static TwitterEntity ConvertToTwitterEntity(String SourceStr) {
        String[] split = SourceStr.split(" ");
        String data = split[1];
        String[] dataSplit = data.split(":");
        return new TwitterEntity().setTwitterAccount(dataSplit[0])
                .setTwitterSourcePassword(dataSplit[1])
                .setEmail(dataSplit[2])
                .setEmailSourcePassword(dataSplit[3])
                .setTwitterSource2faPassword(dataSplit[5]);
    }

}
