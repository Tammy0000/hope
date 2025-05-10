package org.isandy.hope;

import org.isandy.hope.Dao.HopeAuthPathRepository;
import org.isandy.hope.Dao.HopeUserStatusRepository;
import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Entity.Auth.HopeAuthPath;
import org.isandy.hope.Service.AuthUser;
import org.isandy.hope.Service.ProjectService;
import org.isandy.hope.Service.SeleniumService;
import org.isandy.hope.Utils.JwtUtils;
import org.junit.jupiter.api.Test;
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
	HopeAuthPathRepository hopeAuthPathRepository;

	@Test
	void contextLoads() {
		String url = "/user/test/test";
		List<HopeAuthPath> byIsAuth = hopeAuthPathRepository.findByAuth(false);
		for (HopeAuthPath path : byIsAuth) {
			String noAuthPath = path.getPath();
			String substring = noAuthPath.substring(0, noAuthPath.length() - 2);
			if (url.startsWith(substring)) {
				System.out.println(noAuthPath);
				System.out.println(substring);
			}
		}
	}

}
