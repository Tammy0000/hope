package org.isandy.hope;

import org.isandy.hope.Dao.HopeProjectVBARepository;
import org.isandy.hope.Dao.HopeProjectVirtualBrowserRepository;
import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowserLinkAccount;
import org.isandy.hope.Service.*;
import org.isandy.hope.Utils.ChromeLauncher;
import org.isandy.hope.Utils.IpUtil;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
	HopeProjectVirtualBrowserRepository hopeProjectVirtualBrowserRepository;

	@Autowired
	HopeProjectVBARepository hopeProjectVBARepository;

	@Test
	void contextLoads() {
		System.out.println(virtualBrowser.launchBrowser(123456L, "tw"));
	}

}
