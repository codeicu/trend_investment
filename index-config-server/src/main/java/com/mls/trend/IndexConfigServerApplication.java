package com.mls.trend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@EnableEurekaClient
public class IndexConfigServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(IndexConfigServerApplication.class).run(args);

    }
}
