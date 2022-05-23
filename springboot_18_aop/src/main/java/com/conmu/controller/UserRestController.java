package com.conmu.controller;

import com.conmu.po.User;
import com.conmu.response.ResultBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mucongcong
 * @date 2022/05/23 14:44
 * @since
 **/
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
    @GetMapping("/user")
    public ResultBody findByUser(User user) {
        System.out.println("用户查询接口请求的参数:" + user);
        ResultBody resultBody = new ResultBody();
        List<User> userList = new ArrayList<>();
        User user2 = new User();
        user2.setId(1L);
        user2.setName("xuwujing");
        user2.setAge(18);
        userList.add(user2);
        resultBody.setCode("0");
        resultBody.setResult(userList.toString());
        System.out.println("用户查询接口响应的参数:" + resultBody);
        return resultBody;
    }
}

