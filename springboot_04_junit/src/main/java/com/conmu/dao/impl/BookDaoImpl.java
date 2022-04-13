package com.conmu.dao.impl;

import com.conmu.dao.BookDao;
import org.springframework.stereotype.Repository;
//@Component也可以，但是@Respository是写在数据层的
@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("bookDao is running ...");
    }
}
