package com.conmu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.conmu.controller.utils.R;
import com.conmu.domain.Book;
import com.conmu.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService iBookService;

    @GetMapping
    public R getAll() {
        return new R(true,iBookService.list());
    }

    @PostMapping
    public R save(@RequestBody Book book) throws IOException {
        if (book.getName().equals("123")){
            throw new IOException();
        }
        boolean flag = iBookService.save(book);
        return new R(flag, flag ? "添加成功^_^" : "添加失败-_-!");
    }

    @PutMapping
    public R update(@RequestBody Book book) {
        return new R(iBookService.modify(book));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(iBookService.delete(id));
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id) {
        System.out.println("测试热部署...");
        System.out.println("测试热部署...");
        System.out.println("测试热部署...");
        System.out.println("测试热部署...");
        System.out.println("测试热部署...");
        return new R(true,iBookService.getById(id));
    }

//    @GetMapping("{currentPage}/{pageSize}")
//    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
//        IPage<Book> page = iBookService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码值，则重新执行查询操作，将最大页码作为当前页码值
//        if (currentPage > page.getPages()) {
//            page = iBookService.getPage((int)page.getPages(), pageSize);
//        }
//        return new R(true,page);
//    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Book book){
        IPage<Book> page = iBookService.getPage(currentPage, pageSize,book);
        //如果当前页码值大于总页码值，则重新执行查询操作，将最大页码作为当前页码值
        if (currentPage > page.getPages()) {
            page = iBookService.getPage((int)page.getPages(), pageSize,book);
        }
        return new R(true,page);
    }
}
