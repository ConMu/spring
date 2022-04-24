package com.conmu;

import com.conmu.dao.BookDao;
import com.conmu.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Springboot15SqlApplicationTests {

//    @Autowired
//    private BookDao bookDao;
    @Test
    void contextLoads() {
//        bookDao.selectById(1);
//        System.out.println(book);
    }


    @Test
    void testJdbcTemplate(@Autowired JdbcTemplate jdbcTemplate) {
        String sql = "select * from book";
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
//        System.out.println(maps);
        RowMapper<Book> rm = new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Book temp = new Book();
                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setType(resultSet.getString("type"));
                temp.setDescription(resultSet.getString("description"));
                return temp;
            }
        };
        List<Book> list = jdbcTemplate.query(sql, rm);
        System.out.println(list);
    }
}
