package com.mls.trend.config;


import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class MyIpConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;


    /*
    * 指数代码服务要部署集群
    * 用于获取当前微服务的端口
    * */
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event){
        this.serverPort=event.getWebServer().getPort();
    }

    public int getPort(){
        return this.serverPort;

    }





}
