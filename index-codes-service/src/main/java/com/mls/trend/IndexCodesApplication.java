package com.mls.trend;


import brave.sampler.Sampler;
import cn.hutool.core.util.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class IndexCodesApplication {
    public static void main(String[] args){
        new SpringApplicationBuilder(IndexCodesApplication.class).run(args);
    }
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
