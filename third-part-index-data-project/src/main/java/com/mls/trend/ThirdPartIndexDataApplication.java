package com.mls.trend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ThirdPartIndexDataApplication {
    public static void main(String[] args){

        new SpringApplicationBuilder(ThirdPartIndexDataApplication.class).run(args);

    }
}
