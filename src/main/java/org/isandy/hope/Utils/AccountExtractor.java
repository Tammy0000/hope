package org.isandy.hope.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountExtractor {
    public static List<Map<String, String>> extractLoginAndPassword(String filePath) {
        List<Map<String, String>> accountList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String login = null;
            String password = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("login: ")) {
                    login = line.substring(6).trim();
                } else if (line.startsWith("password: ")) {
                    password = line.substring(9).trim();
                }

                // 判断当 login 和 password 都有值后就存入列表，并重置
                if (login != null && password != null) {
                    Map<String, String> account = new HashMap<>();
                    account.put("login", login);
                    account.put("password", password);
                    accountList.add(account);
                    login = null;
                    password = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accountList;
    }
}
