package com.geek.gateway.hystirx;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 默认降级处理
 * @Author: 屈艳锋
 * @Date: 2019/8/15 14:12
 */

@RestController
public class DefaultHystrixController {

    @RequestMapping("/defaultfallback")
    public Map<String,String> defaultfallback(){
        Map<String,String> map = new HashMap<>();
        map.put("resultCode","fail");
        map.put("resultMessage","熔断降级操作...");
        map.put("resultObj","null");
        return map;
    }
}