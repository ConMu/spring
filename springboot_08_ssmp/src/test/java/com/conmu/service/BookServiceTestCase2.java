package com.conmu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.conmu.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTestCase2 {
    @Autowired
    private IBookService bookService;

    @Test
    void testGetById(){
        System.out.println(bookService.getById(2));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试3");
        book.setName("测试名称3");
        book.setDescription("测试描述3");
        bookService.save(book);
    }
    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(3);
        book.setType("测试3");
        book.setName("测试名称3");
        book.setDescription("测试描述3");
        bookService.updateById(book);
    }
    @Test
    void testDelete(){
        bookService.removeById(3);
    }
    @Test
    void testGetAll(){
        System.out.println(bookService.list());
    }
    @Test
    void testGetPage(){
        IPage<Book> bookIPage = new Page<>(1, 3);
        bookService.page(bookIPage);
    }

}
