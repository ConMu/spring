package com.conmu;

import com.conmu.domain.Book;
import com.conmu.service.IBookService;
import com.conmu.service.impl.BookServiceImpl2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional//spring事务，防止测试类执行时污染数据库
@Rollback(true)//回滚
public class DaoTest {
    @Autowired
    private IBookService bookService;
    @Test
    void testSave(){
        Book book = new Book();
        book.setId(1);
        book.setName("springboot4");
        book.setType("a3");
        book.setDescription("This is demo3");
        bookService.save(book);
    }
}
