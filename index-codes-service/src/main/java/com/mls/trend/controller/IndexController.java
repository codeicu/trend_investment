package com.mls.trend.controller;


import com.mls.trend.config.MyIpConfiguration;
import com.mls.trend.entity.Index;
import com.mls.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    MyIpConfiguration ipConfiguration;

    @GetMapping("/codes")
    @CrossOrigin   //跨域
    public List<Index> getCodes() throws Exception{
        System.out.println("current instance's port is "+ipConfiguration.getPort());
        return indexService.get();
    }
}
