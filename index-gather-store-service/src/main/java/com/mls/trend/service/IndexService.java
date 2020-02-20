package com.mls.trend.service;

import cn.hutool.core.collection.CollectionUtil;
import com.mls.trend.entity.Index;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* 指数服务类，用于获取第三方数据
* */
@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {
    private List<Index> indexes;

    @Autowired
    RestTemplate restTemplate;

    @Value("${thirdPart.ipAddress}")
    String thirdPartIpAddress;

    @Value("${thirdPart.port}")
    String thirdPartPort;


    @CacheEvict(allEntries = true)
    public void remove(){
        System.out.println("remove cache...");
    }


    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    @CachePut(key = "'all_codes'",unless = "#result[0].code=='000000'")
    public List<Index> fresh(){
        System.out.println("fresh cache from third part...");
        return fetch_indexes_from_third_part();
    }

    /*@HystrixCommand(fallbackMethod = "third_part_not_connected")*/
    public List<Index> fetch_indexes_from_third_part(){
        List<Map> temp= restTemplate.getForObject("http://"+thirdPartIpAddress+":"+thirdPartPort+"/indexes/codes.json",List.class);
        return map2Index(temp);
    }

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    @Cacheable(key = "'all_codes'")
    public List<Index> get(){
        System.out.println("get from third part...");
        return fetch_indexes_from_third_part();
    }



    public List<Index> third_part_not_connected(){
        System.out.println("Third part index service not connected!");
        Index index = new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return CollectionUtil.toList(index);
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
