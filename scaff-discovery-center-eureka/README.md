###scaff-discovery-center-eureka spring-cloud 组件
基于SpringCloud-eureka实现的注册中心

１．需要引入的ｐｏｍ有：
````
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka-server</artifactId>
    </dependency>
    //通过引入该pom，可以开启eureka的安全登录，如何配置详见下文
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
````
2.配置host中： 127.0.0.1  discovery，这里在配置文件直接引用域名即可

3.配置文件：
````
    server:
      port: 8761                    # 指定该Eureka实例的端口
    
    eureka:
      server:
        enable-self-preservation: false #关闭自我保护，删除已经关停的节点
      instance:
        hostname: discovery         # 指定该Eureka实例的主机名
      client:
        registerWithEureka: false   # 是否在Eureka注册自己
        fetchRegistry: false
        serviceUrl:
          defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
    
    security:  # 配置Eureka的安全登录
      basic:
        enabled: true
      user:    # 配置登录账号名和密码
         name: root
         password: root
    ````
    
    4.在Application中开启Eureka服务器：
    ````
    **
     * Created by xyl on 10/30/17.
     */
    @SpringBootApplication
    @EnableEurekaServer //声明该application运行的是一个eureka服务器
    public class EurekaApplication {
        public static void main(String[] args) {
            SpringApplication.run(EurekaApplication.class,args);
        }
    }
attention:
enable-self-preservation eureka的自我保护机制：Eureka server每分钟收到的心跳续约的数量低于一个阈值=
实例节点的个数×（60/心跳间隔秒数）×自我保护系数，并且持续15分钟，那么将会进入自我保护模式。自我保护模式中，eurekaserver将
不会注销任何实例节点来保护注册表的信息。当高于阈值后，会自动退出自我保护模式。
这一点有一定的设计思想：宁可保留错误的节点信息，也不能随意注销健康的节点，以此最大的可能来保证服务的可利用性。
心跳间隔配置：eureka.instance.lease-renewal-interval-in-seconds
自我保护系数：eureka.server.renewal-percent-threshold（默认0.85）

````

5.运行主类，打开链接：http://discovery:8761/，你将看到eureka的界面，你成功的走出了第一步了！
下一步，我们将会注册几个微服务在注册中心了。

6.实现注册中心的高可用

通过配置文件和启动方式实现高可用。
为了更加接近生产环境，我们这里的配置都用域名来配置：首先修改hosts文件：
    
    127.0.0.1 discovery1 discovery2
项目的配置文件如下：
```
    //指定两组配置。
    ---
    spring:
      profiles: discovery1                                 # 指定profile=discovery1
    server:
      port: 8761
    eureka:
      instance:
        hostname: discovery1                               # 指定当profile=discovery1时，主机名
      client:
        serviceUrl:
          defaultZone: http://discovery2:8762/eureka/      # 将自己注册到discovery2这个Eureka上面去
    //开启安全登录
    security:
      basic:
        enabled: true
      user:
         name: root
         password: root
    ---
    spring:
      profiles: discovery2
    server:
      port: 8762
    eureka:
      instance:
        hostname: discovery2
      client:
        serviceUrl:
          defaultZone: http://discovery1:8761/eureka/
    
    security:
      basic:
        enabled: true
      user:
         name: root
         password: root
```
启动项目:那么你将会看到DS Replicas那一栏就会多出来你的另一台注册中心服务。

```
    java -jar scaff-discovery-center-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=discovery1
    java -jar scaff-discovery-center-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=discovery2

```