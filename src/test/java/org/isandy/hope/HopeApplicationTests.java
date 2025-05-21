package org.isandy.hope;

import org.isandy.hope.Dao.HopeProjectVBARepository;
import org.isandy.hope.Dao.HopeProjectVBRepository;
import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
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
	VirtualBrowser virtualBrowser;

	@Autowired
    HopeProjectVBRepository hopeProjectVBRepository;

	@Autowired
	HopeProjectVBARepository hopeProjectVBARepository;

	@Autowired
	HopeStorage hopeStorage;

	@Autowired
	TwitterSeleniumService twitterSeleniumService;

	@Test
	void contextLoads() throws InterruptedException {
//		seleniumService.TestAdsBrowser();
//		System.out.println("Hello");
		twitterSeleniumService.loginTwitter();
	}

}
