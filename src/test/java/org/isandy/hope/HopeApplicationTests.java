package org.isandy.hope;

import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Entity.HopeUserSecurityMnemonic;
import org.isandy.hope.Utils.MnemonicUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class HopeApplicationTests {

	@Autowired
	MnemonicRepository mnemonicRepository;

	@Test
	void contextLoads() {
		for (int i = 0; i < 20; i++) {
			HopeUserSecurityMnemonic hopeUserSecurityMnemonic = new HopeUserSecurityMnemonic();
			hopeUserSecurityMnemonic.setMnemonic(MnemonicUtils.generateMnemonic())
					.setDescription("测试")
					.setIsUsed(false)
					.setCreateTime(LocalDateTime.now())
					.setCreateUser("admin");
			mnemonicRepository.save(hopeUserSecurityMnemonic);
		}
	}

}
