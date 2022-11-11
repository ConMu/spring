package com.conmu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.conmu.dao.BookDao;
import com.conmu.domain.Book;
import com.conmu.service.IBookService;
import com.conmu.utils.RedisUtil;
import netease.acdtest.utils.UUIDUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl2 extends ServiceImpl<BookDao, Book>implements IBookService {
    @Autowired
    BookDao bookDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Boolean modify(Book book) {
        return bookDao.updateById(book) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        return bookDao.selectPage(page, null);
    }
    @Override
    public IPage<Book> getPage(int currentPage, int pageSize, Book book) {
        //LambdaQueryWrapper需要指定泛型
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        lqw.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        lqw.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        lqw.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());
        IPage page = new Page(currentPage, pageSize);
        return bookDao.selectPage(page, lqw);
    }

    @Override
    public Boolean toSaveRedis(String appKey, String appValue,int cap,int time) {

        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add(appKey);
        String s = UUIDUtils.genUUID();
        keys.add(s);
        values.add(String.valueOf(cap));
        values.add(String.valueOf(time));
        // 执行 lua 脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(tpsLuaScript, Long.class);

        ArrayList<Long> result = (ArrayList) redisTemplate.execute(redisScript, keys, cap, System.currentTimeMillis(),10);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        redisUtil.zremove(appKey,s);
        Double aDouble = redisUtil.zscore(appKey, s);
//        DefaultRedisScript<Long> redisScript2 = new DefaultRedisScript<>(tpsLuaScript2, Long.class);
//        redisTemplate.execute(redisScript2, keys,s);
        System.out.println("================");

        return true;
    }

    private String tpsLuaScript = "local key = KEYS[1]\n" +
            "local capacity = tonumber(ARGV[1])\t\n" +
            "local timestamp = tonumber(ARGV[2])\n" +
            "local expiretime = tonumber(ARGV[3])\n" +
            "local id = KEYS[2]\n" +
            "\n" +
            "local count = redis.call(\"zcard\", key)\n" +
            "local allowed = 0\n" +
            "\n" +
            "if count < capacity then\n" +
            "  redis.call(\"zadd\", key, timestamp, id)\n" +
            "  allowed = 1\n" +
            "  count = count + 1\n" +
            "end\n" +
            "redis.call('EXPIRE', key, expiretime)\n" +
            "return { allowed, count }";

//    private String tpsLuaScript2 = "local key = KEYS[1]\n" +
//            "local id = KEYS[2]\n" +
//            "local value = tonumber(ARGV[1])\n" +
//            "redis.call(\"zrem\", key, value)\n";

//    private String tpsLuaScript = "local key = KEYS[1]\n" +
//        "local capacity = tonumber(ARGV[1])\t\n" +
//        "local time = tonumber(ARGV[2])\n" +
//        "local timestamp1 = tonumber(ARGV[3])\n" +
//        "local timestamp2 = tonumber(ARGV[4])\n" +
//        "local id = KEYS[2]\n" +
//        "\n" +
//        "local count = redis.call(\"zcard\", key)\n" +
//        "local allowed = 0\n" +
//        "\n" +
//        "if count < capacity then\n" +
//        "  redis.call(\"zadd\", key, timestamp1, id)\n" +
//        "  allowed = 1\n" +
//        "  count = count + 1\n" +
//        "end\n" +
//        "redis.call('EXPIRE', key, time)\n" +
//        "redis.call('ZREMRANGEBYSCORE', key, 0, timestamp2)\n" +
//        "return { allowed, count }";

}
