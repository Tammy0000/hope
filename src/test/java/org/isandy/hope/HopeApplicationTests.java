package org.isandy.hope;

import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Service.ProjectService;
import org.isandy.hope.Service.SeleniumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

@SpringBootTest
class HopeApplicationTests {

	@Autowired
	MnemonicRepository mnemonicRepository;

	@Autowired
	ProjectService projectService;

	@Autowired
	SeleniumService seleniumService;

	@Test
	void contextLoads() throws InterruptedException, AWTException {
		seleniumService.updateTwitterPassword(1L);
	}

}
