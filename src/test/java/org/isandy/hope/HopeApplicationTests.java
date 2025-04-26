package org.isandy.hope;

import org.isandy.hope.Service.IChromeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HopeApplicationTests {
	@Autowired
	IChromeService chromeService;

	@Test
	void contextLoads() throws InterruptedException {
		chromeService.start();
	}

}
