package com.conmu.dao;

import com.conmu.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookDao {
    @Select("select * from book where id = #{id}")
    public Book getById(Integer id);

}
