package com.conmu;

import com.conmu.mapper.AppDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot19MybatisGeneratorApplicationTests {

	@Autowired
	private AppDAO appDao;
	@Test
	void contextLoads() {
		System.out.println(appDao.selectByPrimaryKey(1L));
	}
}
