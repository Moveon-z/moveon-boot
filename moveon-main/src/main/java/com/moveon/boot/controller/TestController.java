package com.moveon.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/9 16:59
 * @Version 1.0
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(List<Object> list) {
        return list.toString();
    }

}
