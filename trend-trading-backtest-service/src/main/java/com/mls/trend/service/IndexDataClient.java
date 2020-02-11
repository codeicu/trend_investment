package com.mls.trend.service;


import com.mls.trend.entity.IndexData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/*
*如果 INDEX-DATA-SERVICE 不可用或者不可访问,会发生熔断
* 当熔断发生的时候，对应的方法就会被调用 IndexDataClientFeignHystrix.getIndexData()
* 返回0000-00-00
* */
@FeignClient(value = "INDEX-DATA-SERVICE",fallback = IndexDataClientFeignHystrix.class)
public interface IndexDataClient {
    @GetMapping("/data/{code}")
    public List<IndexData> getIndexData(@PathVariable("code") String code);

}
