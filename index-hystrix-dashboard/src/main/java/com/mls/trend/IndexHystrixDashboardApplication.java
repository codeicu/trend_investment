package com.mls.trend;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class IndexHystrixDashboardApplication {
    public static void main(String[] args) {


        new SpringApplicationBuilder(IndexHystrixDashboardApplication.class).run(args);

    }

}