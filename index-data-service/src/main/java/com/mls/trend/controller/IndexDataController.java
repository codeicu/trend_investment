package com.mls.trend.controller;

import com.mls.trend.config.IpConfiguration;
import com.mls.trend.entity.IndexData;
import com.mls.trend.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexDataController {
    @Autowired
    IndexDataService indexDataService;

    @Autowired
    IpConfiguration ipConfiguration;

    @GetMapping("/data/{code}")
    public List<IndexData> getIndexData(@PathVariable("code") String code){
        System.out.println("current instance is :"+ipConfiguration.getPort());
        return indexDataService.get(code);
    }
}
