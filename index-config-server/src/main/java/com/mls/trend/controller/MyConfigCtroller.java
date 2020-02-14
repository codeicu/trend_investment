package com.mls.trend.controller;

import com.mls.trend.util.FreshConfigUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyConfigCtroller {
    @GetMapping("/freshConfig")
    public String fresh(){
        FreshConfigUtil freshConfigUtil=new FreshConfigUtil();
        freshConfigUtil.freshConfig();
        return "成功";
    }
}
