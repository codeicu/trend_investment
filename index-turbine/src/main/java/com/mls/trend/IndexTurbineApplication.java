package com.mls.trend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;


@SpringBootApplication
@EnableTurbine
public class IndexTurbineApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(IndexTurbineApplication.class).run(args);

    }

}