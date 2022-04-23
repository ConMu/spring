package com.conmu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.conmu.dao.BookDao;
import com.conmu.domain.Book;
import com.conmu.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl2 extends ServiceImpl<BookDao, Book>implements IBookService {
    @Autowired
    BookDao bookDao;

    @Override
    public boolean save(Book book) {
        return bookDao.insert(book) > 0;
    }

//    @Override
//    public Boolean modify(Book book) {
//        return bookDao.updateById(book) > 0;
//    }
//
//    @Override
//    public Boolean delete(Integer id) {
//        return bookDao.deleteById(id) > 0;
//    }
//
//    @Override
//    public IPage<Book> getPage(int currentPage, int pageSize) {
//        IPage page = new Page(currentPage, pageSize);
//        return bookDao.selectPage(page, null);
//    }
//    @Override
//    public IPage<Book> getPage(int currentPage, int pageSize, Book book) {
//        //LambdaQueryWrapper需要指定泛型
//        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
//        lqw.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
//        lqw.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
//        lqw.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());
//        IPage page = new Page(currentPage, pageSize);
//        return bookDao.selectPage(page, lqw);
//    }

}
