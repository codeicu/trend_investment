package com.mls.trend.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.mls.trend.entity.IndexData;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
    private Map<String, List<IndexData>> indexDatas=new HashMap<>();

    @Autowired
    RestTemplate restTemplate;


    @CacheEvict(key = "'indexData-code-'+#p0")
    public void remove(String code){
        System.out.println("remove indexDatas...");

    }

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    @CachePut(key="'indexData-code-'+ #p0",unless = "#result[0].closePoint==0.0")
    public List<IndexData> fresh(String code){
        System.out.println("fresh indexDatas...");
        return fetch_indexDatas_from_third_part(code);
    }

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    @Cacheable(key="'indexData-code-'+ #p0")
    public List<IndexData> get(String code){
        System.out.println("get form third part...");
        return fetch_indexDatas_from_third_part(code);
    }


    public List<IndexData> fetch_indexDatas_from_third_part(String code){
        List<Map> temp=restTemplate.getForObject("http://localhost:8090/indexes/"+code+".json",List.class);
        return map2IndexData(temp);
    }

    private List<IndexData> map2IndexData(List<Map> temp){
        List<IndexData> indexDatas=new ArrayList<>();
        for(Map map:temp){
            String date=map.get("date").toString();
            float closePoint= Convert.toFloat(map.get("closePoint"));
            IndexData indexData = new IndexData();
            indexData.setDate(date);
            indexData.setClosePoint(closePoint);
            indexDatas.add(indexData);
        }
        return indexDatas;

    }

    public List<IndexData> third_part_not_connected(String code){
        System.out.println("third_part_not_connected...");
        IndexData index= new IndexData();
        index.setClosePoint(0);
        index.setDate("n/a");
        return CollectionUtil.toList(index);
    }


}
