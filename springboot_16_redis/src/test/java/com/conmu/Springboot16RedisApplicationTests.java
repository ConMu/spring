package com.conmu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class Springboot16RedisApplicationTests {
	@Autowired
	//以object 对象 为操作的基本单元，因此操作后在客户端不能查询出来
	private RedisTemplate redisTemplate;

	@Test
	void set() {
		ValueOperations ops = redisTemplate.opsForValue();
		ops.set("age", 41);
	}
	@Test
	void get() {
		ValueOperations ops = redisTemplate.opsForValue();
		Object age = ops.get("age");
		System.out.println(age);
	}

	@Test
	void hset() {
		HashOperations ops = redisTemplate.opsForHash();
		ops.put("info","a","aa1");
		ops.put("info","b","aa2");
	}
	@Test
	void hget() {
		HashOperations ops = redisTemplate.opsForHash();
		Object o1 = ops.get("info", "a");
		Object o2 = ops.get("info", "b");
		System.out.println(o1);
		System.out.println(o2);
	}


}
