package com.conmu.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.conmu.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTestCase {
    @Autowired
    private BookService bookService;

    @Test
    void testGetById(){
        System.out.println(bookService.getById(2));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试1");
        book.setName("测试名称");
        book.setDescription("测试描述");
        bookService.save(book);
    }
    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(3);
        book.setType("测试3");
        book.setName("测试名称3");
        book.setDescription("测试描述3");
        bookService.update(book);
    }
    @Test
    void testDelete(){
        bookService.delete(3);
    }
    @Test
    void testGetAll(){
        System.out.println(bookService.getAll());
    }
    @Test
    void testGetPage(){
        bookService.getPage(1, 5);
    }

}
