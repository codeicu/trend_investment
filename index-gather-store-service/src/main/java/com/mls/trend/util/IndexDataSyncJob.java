package com.mls.trend.util;

import cn.hutool.core.date.DateUtil;
import com.mls.trend.entity.Index;
import com.mls.trend.service.IndexDataService;
import com.mls.trend.service.IndexService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

/*
* 指数数据刷新
* 引用定时器机制：
* quartz
* */
public class IndexDataSyncJob extends QuartzJobBean {
    @Autowired
    private IndexService indexService;

    @Autowired
    private IndexDataService indexDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时器启动:"+ DateUtil.now());
        List<Index> indexes=indexService.fresh();
        for(Index index:indexes){
            indexDataService.fresh(index.getCode());
        }
        System.out.println("定时器结束:"+DateUtil.now());
    }
}
