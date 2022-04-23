package com.conmu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

//定义web环境
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//测试环境中发起的mvc调用是虚拟的调用，需要开启MVC虚拟调用模式
@AutoConfigureMockMvc
public class WebTest {
    @Test
    void test(){
    }

    @Test
    void testWeb(@Autowired MockMvc mvc) throws Exception {
        //http://localhost:8081/books
        //创建虚拟请求，当前访问localhost:8081/books
        RequestBuilder builder = MockMvcRequestBuilders.get("http://localhost:8081/books");
        //执行对应请求
        mvc.perform(builder);
    }

    @Test
    void testStatus(@Autowired MockMvc mvc) throws Exception {
        //http://localhost:8081/books
        //创建虚拟请求，当前访问localhost:8081/books
        RequestBuilder builder = MockMvcRequestBuilders.get("http://localhost:8081/books1");
        //执行对应请求
        ResultActions action = mvc.perform(builder);
        //设定预期值，与真实值对比，成功测试通过，失败测试失败
        //定义本次调用的预期值
        StatusResultMatchers status = MockMvcResultMatchers.status();
        //预计本次调用时成功的状态.200  ->  匹配状态
        ResultMatcher ok = status.isOk();
        //添加预计值到本次调用过程中进行匹配
        action.andExpect(ok);
    }
    //匹配执行结果
    @Test
    void testBody(@Autowired MockMvc mvc) throws Exception {
        //http://localhost:8081/books
        //创建虚拟请求，当前访问localhost:8081/books
        RequestBuilder builder = MockMvcRequestBuilders.get("http://localhost:8081/books");
        //执行对应请求
        ResultActions action = mvc.perform(builder);
        //设定预期值，与真实值对比，成功测试通过，失败测试失败
        //定义本次调用的预期值
        ContentResultMatchers content = MockMvcResultMatchers.content();
        //预计本次调用时成功的body  ->  匹配body
        ResultMatcher result = content.string("springboot2");
        //添加预计值到本次调用过程中进行匹配
        action.andExpect(result);
    }
    //匹配json
    @Test
    void testJson(@Autowired MockMvc mvc) throws Exception {
        //http://localhost:8081/books
        //创建虚拟请求，当前访问localhost:8081/books
        RequestBuilder builder = MockMvcRequestBuilders.get("http://localhost:8081/books");
        //执行对应请求
        ResultActions action = mvc.perform(builder);
        //设定预期值，与真实值对比，成功测试通过，失败测试失败
        //定义本次调用的预期值
        ContentResultMatchers content = MockMvcResultMatchers.content();
        //预计本次调用时成功的body  ->  匹配body
        ResultMatcher result = content.json("{\"id\":1,\"type\":\"a\",\"name\":\"springboot2\",\"description\":\"This is demo\"}");
        //添加预计值到本次调用过程中进行匹配
        action.andExpect(result);
    }

    //匹配content type
    @Test
    void testContenttype(@Autowired MockMvc mvc) throws Exception {
        //http://localhost:8081/books
        //创建虚拟请求，当前访问localhost:8081/books
        RequestBuilder builder = MockMvcRequestBuilders.get("http://localhost:8081/books");
        //执行对应请求
        ResultActions action = mvc.perform(builder);
        //设定预期值，与真实值对比，成功测试通过，失败测试失败
        //定义本次调用的预期值
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        //预计本次调用时成功的body  ->  匹配body
        ResultMatcher string = header.string("Content-Type", "application/json");
        action.andExpect(string);
    }

    @Test //平时测试的步骤
    void testGetById(@Autowired MockMvc mvc) throws Exception {
        //http://localhost:8081/books
        //创建虚拟请求，当前访问localhost:8081/books
        RequestBuilder builder = MockMvcRequestBuilders.get("http://localhost:8081/books");
        //执行对应请求
        ResultActions action = mvc.perform(builder);
        //设定预期值，与真实值对比，成功测试通过，失败测试失败

        StatusResultMatchers status = MockMvcResultMatchers.status();
        //预计本次调用时成功的状态.200  ->  1. 匹配状态
        ResultMatcher ok = status.isOk();

        HeaderResultMatchers header = MockMvcResultMatchers.header();
        //预计本次调用时成功的content type  ->  2. 匹配content type
        ResultMatcher string = header.string("Content-Type", "application/json");
        action.andExpect(string);

        ContentResultMatchers content = MockMvcResultMatchers.content();
        //预计本次调用时成功的body  ->  3. 匹配body
        ResultMatcher result = content.json("{\"id\":1,\"type\":\"a\",\"name\":\"springboot\",\"description\":\"This is demo\"}");
        //添加预计值到本次调用过程中进行匹配
        action.andExpect(result);
    }
}
