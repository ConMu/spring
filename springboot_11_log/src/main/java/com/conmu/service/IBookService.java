package com.conmu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.conmu.domain.Book;
import org.springframework.stereotype.Service;


public interface IBookService extends IService<Book> {
    Boolean modify(Book book);

    Boolean delete(Integer id);

    IPage<Book> getPage(int currentPage, int pageSize, Book book);

    Boolean saveBook(Book book);

}
