package org.isandy.hope;

import org.isandy.hope.Dao.HopeProjectAccountTypeRepository;
import org.isandy.hope.Dao.HopeProjectVBARepository;
import org.isandy.hope.Dao.HopeProjectVirtualBrowserRepository;
import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Entity.Project.HopeProjectAccountType;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowserLinkAccount;
import org.isandy.hope.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

	@Autowired
	HopeProjectVBARepository hopeProjectVBARepository;

	@Autowired
	HopeProjectAccountTypeRepository  hopeProjectAccountTypeRepository;

	@Test
	void contextLoads() {
		List<HopeProjectVirtualBrowserLinkAccount> byUserId = hopeProjectVBARepository.findByUserId(12345678L);
		HopeProjectAccountType byTypeId = hopeProjectAccountTypeRepository.findByTypeId(1L);
		for (HopeProjectVirtualBrowserLinkAccount linkAccount : byUserId) {
			linkAccount.setHopeProjectAccountType(byTypeId);
			byTypeId.getHopeProjectVirtualBrowserLinkAccounts().add(linkAccount);
			hopeProjectAccountTypeRepository.save(byTypeId);
		}
	}

}
