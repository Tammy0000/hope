package org.isandy.hope;

import org.isandy.hope.Dao.HopeProjectVirtualBrowserRepository;
import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowserLinkAccount;
import org.isandy.hope.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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

	@Autowired
	HopeProjectVirtualBrowserRepository hopeProjectVirtualBrowserRepository;

	@Test
	void contextLoads() {
		System.out.println(virtualBrowser.getBrowserList());
	}

}
