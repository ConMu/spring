package com.conmu;

import com.conmu.testcase.domain.BookCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
//properties 可为当前测试环境提供临时属性，不影响整体属性
//@SpringBootTest(properties = {"test.prop=testValue1"})
//args 可为当前测试环境提供临时属性，不影响整体属性 级别比properties高
//@SpringBootTest(args = {"--test.prop=testValue2"})
@SpringBootTest(args = {"--test.prop=testValue2"},properties = {"test.prop=testValue3"})
public class PropertiesAndArgsTest {


    @Value("${test.prop}")
    private String msg;
    @Autowired
    private BookCase bookCase;
    @Test
    void testProperties() {
        System.out.println(msg);
        System.out.println(bookCase);
    }
}
