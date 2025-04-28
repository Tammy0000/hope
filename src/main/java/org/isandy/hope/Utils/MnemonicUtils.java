package org.isandy.hope.Utils;

import org.bitcoinj.crypto.MnemonicCode;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * @description: 助记词生成工具
 * @author Tammy
 * @date 2025/4/27 下午5:00
 */
public class MnemonicUtils {
    // 生成助记词
    public static String generateMnemonic()  {
        // 生成128位的随机种子
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[16];  // 128 bits
        secureRandom.nextBytes(entropy);

        // 使用 BitcoinJ 库来生成助记词
        MnemonicCode mnemonicCode;
        try {
            mnemonicCode = new MnemonicCode();
        } catch (IOException e) {
            return null;
        }
        List<String> mnemonicWords = mnemonicCode.toMnemonic(entropy);

        // 将生成的助记词转为字符串，单词之间用空格分隔
        StringBuilder mnemonic = new StringBuilder();
        var index = 0;
        for (String word : mnemonicWords) {
            index ++;
            if (index == 12) {
                mnemonic.append(word);
            }
            if (index < 12) {
                mnemonic.append(word).append("-");
            }
        }
        return mnemonic.toString().trim();
    }
}
