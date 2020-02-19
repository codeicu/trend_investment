package com.mls.trend;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCaching
public class IndexGatherStoreApplication {
    public static void main(String[] args){


        new SpringApplicationBuilder(IndexGatherStoreApplication.class).run(args);

    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
