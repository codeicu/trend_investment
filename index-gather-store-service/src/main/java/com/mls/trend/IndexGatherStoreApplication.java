package com.mls.trend;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class IndexGatherStoreApplication {
    public static void main(String[] args){
        int port=0;
        int defaultPort=8001;
        int eurekaServerPort=8761;
        port=defaultPort;
        if(NetUtil.isUsableLocalPort(eurekaServerPort)){
            System.err.printf("检查到端口%d 未启用，eureka 服务器没有启动，数据采集存储服务无法使用，故退出%n", eurekaServerPort );
            System.exit(1);
        }

        if(null!=args && 0!=args.length) {
            for (String arg : args) {
                if(arg.startsWith("port=")) {
                    String strPort= StrUtil.subAfter(arg, "port=", true);
                    if(NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        }

        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动数据采集存储服务%n", port );
            System.exit(1);
        }

        new SpringApplicationBuilder(IndexGatherStoreApplication.class).properties("server.port="+port).run(args);

    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}