package org.isandy.hope;

import org.isandy.hope.Dao.HopeProjectTwitterRepository;
import org.isandy.hope.Dao.HopeProjectVBARepository;
import org.isandy.hope.Dao.HopeProjectVBRepository;
import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Entity.Project.HopeProjectTwitter;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Service.*;
import org.isandy.hope.Utils.AccountExtractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
		twitterSeleniumService.loginTwitter();
	}

}
