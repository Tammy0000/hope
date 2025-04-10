package org.isandy.hope;

import org.isandy.hope.Dao.NewEntityRepository;
import org.isandy.hope.Dto.NewEntityDto;
import org.isandy.hope.Dto.NewEntityDtoPro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HopeApplicationTests {
	@Autowired
	private NewEntityRepository newEntityRepository;

	@Test
	void contextLoads() {
		List<NewEntityDtoPro> ids = newEntityRepository.findByIds(1L);
		NewEntityDtoPro first = ids.getFirst();
		System.out.println(first.getName());
		System.out.println(first.getDescription());
	}

}
