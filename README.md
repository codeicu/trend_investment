# 毕业设计_基于SpringCloud的趋势投资分析系统

##介绍

###**毕业设计项目：基于SpringCloud的趋势投资分析系统**
<center><a href="http://47.93.188.100:8031/api-view/" target="_blank">点我进入项目主页</a></center>
* **研究的主要内容：**
	1. 根据金融投资领域的量化投资、趋势追踪、移动均线等理论，设计趋势投资算法。
	2. 分布式和集群开发与[^ECS] 部署。  
        *   [^springCloud]  :[^Eureka server]+[^Eureka client]
        * 微服务之间通过http进行轻量通信：直接在properties或者yml配置访问url、微服务[^@enableDiscovery]、[^Feign]
        * 对其中四个核心微服务进行集群，通过[^zuul]进行网关转发与基本的轮询式负载均衡。
        *    [^redis]作为缓存，[^rabbitmq]消息队列+bus总线实现动态配置，[^zipkin]查看服务调用链，[^hystrix dashboard]+[^turbine]实现对集群的聚合监控  
  
	3. 根据从第三方获取到的原生资料，因为能力有限无法获取实时数据，故利用已有历史数据，用趋势投资算法对常见指数进行模拟回测，分析其收益与风险。
  
* **系统功能**

  1. 通过切换不同的指数，可以在<u>曲线分布图</u>上看到指数收益以及趋势投资收益的可视化区别

  2. 在<u>收益一览</u>可以看到数字话的趋势投资收益比较

  3. 在<u>交易统计</u>可以看到总共多少次交易，盈亏比，平均盈利比率，亏损比率。

  4. 在<u>收益分布对比表</u>可以看到看到不同年份的收益比较表

  5. 在<u>收益分布对比图</u>可以看到不同年份的收益比较图

  6. 在<u>交易明细</u>可以看到每一笔交易的开始结束时间，对应的收盘点以及盈亏情况
     除此之外还可以调整参数观察不同参数情况下的趋势投资收益变化
* 调整**均线**
  
* 调整**买入阈值**
  
* 调整**卖出阈值**
  
* 引入**手续费**计算
  
* 调制**开始** **结束**日期

**软件1604 马力神**

***QQ***:**1045772673**

**中国石油大学（华东）**

##软件架构

**为了完成这个项目，都用到了以下的技术**

* Java语言开发
* 前端
  html, CSS, Javascript, Axios, JQuery ,Bootstrap, Vue.js,[^Chart.js]
* 后端框架部分
    [^springmvc] ,[^springCloud/springBoot]
* 其他
  [^redis],[^rabbitmq]，[^zipkin],[^turbine],[^hystrix dashboard],[^quartz]，[^docker]

**开发工具**

 [^Intellij IDEA],[^Maven]，[^git]

##工作进展

（项目已完成）

* 2020-02-14 21:23
  * [x] 周边服务：断路器聚合监控   

* 2020-02-14 20:04
  * [x] 周边服务：MQ总线

* 2020-02-14 17:09
  * [x] 周边服务：视图微服务注册configCli

* 2020-02-14 15:19
  * [x] 周边服务：配置信息微服务

* 2020-02-14 14:57
  * [x] 周边服务：zipkin链路追踪

* 2020-02-14 12:53 
  * [x] 年收益分布对比

* 2020-02-13 23:08
  - [x] 模拟回测-收益对比、交易统计、交易明细

* 2020-02-11 22:31
  - [x] 模拟回测-收益对比

* 2020-02-11 20:08
  - [x] 视图-日期控件

* 2020-02-11 19:35  
  
  * [x] 视图-指数基础数据及切换指数
* 2020-02-11 17:50  
  
  * [x] 视图-指数代码
* 2020-02-11 15:09  
  
  * [x] 模拟回测视图
* 2020-02-11 14:30
  
  * [x] 模拟回测数据微服务
* 2020-02-10 19:59
  
  * [x] 网关 

* 2020-02-10 00:33
  * [x] 指数数据微服务

* 2020-01-23 23:04:
  
  * [x] 数据获取存储：定时器
* 2020-01-19 16:58:
  
  * [x] 数据采集存储：指数数据
* 2020-01-19 15:56:
  
  * [x] 采集存储：刷新redis
* 2020-01-19 11:16:
  
  * [x] 数据采集存储：断路器
* 2020-01-18 19:18:
  
  * [x] 数据采集存储服务
* 2020-01-18 18:05:
  
  * [x] 第三方指数服务
* 2020-01-18 15:12:
  
  * [x] 微服务：注册中心
* 2020-01-17：
  
  * [x] 创建父子项目,项目启动

##分布式部署教程

* **环境准备**:  

  *     [^ECS]多台  

    1. OS:**Linux centOs7.x x64**  (如果使用**docker**安装rabbitmq和redis,要求内核高于**3.10**版本以及**64位**机器)  
    2.  [安装jdk1.8](https://www.cnblogs.com/sxdcgaq8080/p/7492426.html)
    3. 设置服务器安全组规则开放对应端口;防火墙也要开放对应端口,或者关闭防火墙(不推荐)
        > ```shell
        > #systemctl status firewalld        //查看防火墙状态```
        > #systemctl start firewalld      //开启防火墙```
        > #firewall-cmd --list-all      //查看当前放行策略```
        > firewall-cmd --permanent --zone=public --add-port=8080/tcp
        > 参数介绍：
        > 1、firewall-cmd：是Linux提供的操作firewall的一个工具；
        > 2、--permanent：表示设置为持久；
        > 3、--add-port：标识添加的端口；
        > #firewall-cmd --reload         //重新加载配置使生效
        > ```
     4. 安装一台**redis-server**，一台**rabbitmq-server**
		
        * **version:** rabbitmq 3.7.24-management        redis 5.0.7   
        * rabbitmq需要**erlang**环境支持，参考rabbitmq的github官方教程[安装erlang](https://github.com/rabbitmq/erlang-rpm)
        * docker启动redis和rabbitmq需要使用-p指定端口映射，最好-v进行配置文件映射，具体教程请自行[百度](www.baidu.com)

* **部署微服务**  
  * 微服务
		eureka-server:注册中心
		index-gather-store-service：第三方数据采集服务
		third-part-index-data-project：第三方数据中心
		index-config-server：配置中心
		index-codes-server：指数代码服务
		index-data-server：指数数据服务
		trend-trading-backtest-service：模拟回测业务服务
		trend-trading-backtest-view：模拟回测视图服务
		index-hystrix-dashboard：断路器监控服务
		zipkin-server：链路追踪服务
		index-turbine：断路器聚合服务
		index-zuul-service ：网关转发中心服务
		
  * 部署顺序
  
	graph LR 
	  C(链路追踪服务)-->C(注册中心)
	  C(注册中心)-->C(第三方数据中心)
	  C(第三方数据中心)-->C(第三方数据采集服务)
	  C(第三方数据采集服务)-->C(配置中心)
	  C(配置中心)-->C(指数代码服务集群)
	  C(指数代码服务)-->C(指数数据服务集群)
	  C(指数数据服务)-->C(模拟回测业务服务集群)
	  C(模拟回测业务服务集群)-->C(模拟回测视图服务集群)
	  C(模拟回测视图服务集群)-->C(断路器监控服务)
	  C(断路器监控服务)-->C(断路器聚合服务)
	  C(断路器聚合服务)-->C(网关转发中心服务)

	
  
  
  
  ```
  
  ```

##码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)



[^ECS]: 弹性云服务器
[^Eureka server]: 微服务注册中心
[^Eureka client]: 微服务客户端
[^.properties]: 点进式配置文件
[^.yml]: 缩进式配置文件
[^Feign]: 声明式web service
[^@enableDiscovery]: 可发现其他微服务
[^zuul]: 网关客户端
[^zipkin]: 链路追踪客户端
[^hystrix dashboard]: 断路器监控客户端
[^turbine]: 聚合器
[^rabbitmq]: 消息中间件
[^redis]: 缓存中间件
[^Chart.js]: 绘图表js
[^springCloud/springBoot]: 一站式分布式解决方案
[^springmvc]: web模块
[^Intellij IDEA]: IDE
[^Maven]: 项目构建工具
[^springCloud]: 一站式分布式解决方案
[^git]: 迭代管理工具
[^quartz]: 定时器
[^docker]: 容器