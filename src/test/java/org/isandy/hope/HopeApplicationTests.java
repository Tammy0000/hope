package org.isandy.hope;

import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HopeApplicationTests {

	@Autowired
	MnemonicRepository mnemonicRepository;

	@Autowired
	ProjectService projectService;

	@Autowired
	SeleniumService seleniumService;

	@Autowired
	AuthUser authUser;

	@Autowired
	TwitterSeleniumService twitterSeleniumService;

	@Autowired
	VirtualBrowser virtualBrowser;

	@Test
	void contextLoads() {
//		seleniumService.TestAdsBrowser();
		System.out.println(virtualBrowser.launchBrowser(10));
	}

}
