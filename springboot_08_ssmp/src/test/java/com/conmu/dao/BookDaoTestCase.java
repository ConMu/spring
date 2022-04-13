package com.conmu.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.conmu.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTestCase {
    @Autowired
    BookDao bookDao;

    @Test
    void testGetById(){
        System.out.println(bookDao.selectById(1));
    }
    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试1");
        book.setName("测试名称");
        book.setDescription("测试描述");
        bookDao.insert(book);
    }
    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(3);
        book.setType("测试3");
        book.setName("测试名称3");
        book.setDescription("测试描述3");
        bookDao.updateById(book);
    }
    @Test
    void testDelete(){
        bookDao.deleteById(3);
    }
    @Test
    void testGetAll(){
        System.out.println(bookDao.selectList(null));
    }
    @Test
    void testGetPage(){
        //分页需要用拦截器，mp没有提供配置方式
        IPage page = new Page(1, 5);
        bookDao.selectPage(page, null);
    }
    @Test
    void testGetBy(){
        //select * from book where name like %Spring%
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.like("name", "Spring");
        bookDao.selectList(qw);
    }

    @Test
    void testGetBy2(){
        String name = "Spring";
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
//		if (name != null) {
//			lqw.like(Book::getName,name);
//		}
        lqw.like(name != null, Book::getName, name);
        bookDao.selectList(lqw);
    }
}
