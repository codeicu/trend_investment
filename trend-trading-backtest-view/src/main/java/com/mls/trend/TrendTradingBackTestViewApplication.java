package com.mls.trend;


import brave.sampler.Sampler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class TrendTradingBackTestViewApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(TrendTradingBackTestViewApplication.class).run(args);

    }
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}