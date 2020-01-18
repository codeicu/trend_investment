package com.mls.trend.controller;

import com.mls.trend.entity.Index;
import com.mls.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
    @Autowired
    IndexService indexService;

    @GetMapping("/getCodes")
    public List<Index> getCodes() throws Exception{
        return indexService.fetch_indexes_from_third_part();
    }
}
