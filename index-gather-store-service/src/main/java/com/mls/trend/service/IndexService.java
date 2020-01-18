package com.mls.trend.service;

import com.mls.trend.entity.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* 指数服务类，用于获取第三方数据
* */
@Service
public class IndexService {
    private List<Index> indexes;

    @Autowired
    RestTemplate restTemplate;

    public List<Index> fetch_indexes_from_third_part(){
        List<Map> temp= restTemplate.getForObject("http://localhost:8090/indexes/codes.json",List.class);
        return map2Index(temp);

    }

    private List<Index> map2Index(List<Map> temp) {
        List<Index> indexes =new ArrayList<>();
        for(Map map:temp){
            String code=map.get("code").toString();
            String name=map.get("name").toString();
            Index index=new Index();
            index.setCode(code);
            index.setName(name);
            indexes.add(index);
        }
        return indexes;
    }


}
