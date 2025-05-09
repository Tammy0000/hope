package org.isandy.hope;

import org.isandy.hope.Dao.MnemonicRepository;
import org.isandy.hope.Service.ProjectService;
import org.isandy.hope.Service.SeleniumService;
import org.isandy.hope.Utils.ChromeLauncher;
import org.isandy.hope.Utils.Extractor2FA;
import org.isandy.hope.Utils.HttpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.net.http.HttpResponse;

@SpringBootTest
class HopeApplicationTests {

	@Autowired
	MnemonicRepository mnemonicRepository;

	@Autowired
	ProjectService projectService;

	@Autowired
	SeleniumService seleniumService;

	@Test
	void contextLoads() throws Exception {
//		seleniumService.updateTwitterPassword(1L);
//		HttpResponse<String> response = HttpUtils.get("https://2fa.fb.rip/api/otp/5BDPBK472GNVJUAM");
//		System.out.println(Extractor2FA.extractOtp(response.body()));
		ChromeLauncher.launch(9222, "C:/tmp/Crap1982", "https://x.com/");
	}

}
