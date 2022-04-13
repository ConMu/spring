package com.conmu;

import com.conmu.dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest//(classes = Springboot04JunitApplication.class)
class Springboot04JunitApplicationTests {


    //注入要测试的对象
    @Autowired
    private BookDao bookDao;
    @Test
    void contextLoads() {
        System.out.println("test...");
        //执行要测试的对象
        bookDao.save();

    }

}
