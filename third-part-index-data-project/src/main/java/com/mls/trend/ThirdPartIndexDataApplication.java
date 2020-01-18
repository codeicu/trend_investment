package com.mls.trend;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ThirdPartIndexDataApplication {
    public static void main(String[] args){
        int port=8090;
        int eurekaServerPort=8761;

        if(NetUtil.isUsableLocalPort(eurekaServerPort)){
            System.err.printf("检查到端口%d未启用，eureka服务器未启动，第三方指数服务无法使用，故推出%n",eurekaServerPort);
            System.exit(1);
        }

//        判断是否有运行参数，用于配置端口
        if(null!=args && 0!=args.length){
            for(String arg:args){
                if(arg.startsWith("port=")){
                    String strPort= StrUtil.subAfter(arg,"port=",true);
                    if(NumberUtil.isNumber(strPort)){
                        port= Convert.toInt(strPort);
                    }
                }
            }
        }

        if(!NetUtil.isUsableLocalPort(port)){
            System.err.printf("端口%d被占用，无法启动第三方指数服务%n",port);
            System.exit(1);
        }

        new SpringApplicationBuilder(ThirdPartIndexDataApplication.class).properties("server.port="+port).run(args);

    }
}
