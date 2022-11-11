package com.conmu.controller.innerapi;


import com.conmu.domain.Book;
import com.conmu.service.IBookService;
import com.conmu.utils.R;
//import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//@Slf4j
@ControllerAdvice
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService iBookService;

    private static final Logger logger = LoggerFactory.getLogger("log");

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public R getAll() {
        return new R(true,iBookService.list());
    }



//    @RequestMapping(method = RequestMethod.POST)com.alibaba.fastjson
    @PostMapping
    public R test(@RequestBody Book book) {
        logger.info("aaaaa");
        iBookService.saveBook(book);
        return new R(true);
    }

//    @RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public R update(@RequestBody Book book) {
        logger.info("bbbbb");
        return new R(iBookService.modify(book));
    }

//    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @DeleteMapping
    public R delete(@PathVariable Integer id) {
        logger.info("ccccc");
        return new R(iBookService.delete(id));
    }

//    @RequestMapping(value = "{id}",method = RequestMethod.GET)
//    @GetMapping()
//    public R getById(@PathVariable Integer id) {
//        return new R(true,iBookService.getById(id));
//    }

//    @RequestMapping(value = "{currentPage}/{pageSize}",method = RequestMethod.GET)
//    @GetMapping
//    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Book book){
//        IPage<Book> page = iBookService.getPage(currentPage, pageSize,book);
//        //如果当前页码值大于总页码值，则重新执行查询操作，将最大页码作为当前页码值
//        if (currentPage > page.getPages()) {
//            page = iBookService.getPage((int)page.getPages(), pageSize,book);
//        }
//        return new R(true,page);
//    }
}
