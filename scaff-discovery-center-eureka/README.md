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
````
5.运行主类，打开链接：http://discovery:8761/，你将看到eureka的界面，你成功的走出了第一步了！
下一步，我们将会注册几个微服务在注册中心了。