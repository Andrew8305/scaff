###business-source-manager 业务模块
基于SpringCloud-eureka实现的服务提供者

１．需要引入的pom有：
````
        <!-- 添加Eureka的依赖 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <!-- 添加通用web的依赖-->
                <dependency>
                    <groupId>scaff</groupId>
                    <artifactId>common-web</artifactId>
                    <version>1.0-SNAPSHOT</version>
                </dependency>
````
2.功能定义
    
    1.上传一个人的数据，并调用数据解析模块进行解析。这里通过注册中心的service_id进行调用。
    2.数据解析模块会对该人数据进行简单判断，返回该人是好人true还是坏人false
    
3.整合ribbon客户端以及熔断器Hystrix
    
    1.ribbon说白一点就是一个发起http请求的客户端，在springcloud使用的是RestTemplate。
    通过该客户端可以实现负载均衡，以及一些自定义的路由条件。这里详细可以看zuul网关模块的RestTemplate的自定义的规则。
    
    2.Hystrix被称为熔断器，为什么叫做熔断器，就是在某一微服务发生故障性阻断时，这里会作为该微服务的
    备用入口登场，从本质上来保证整个服务调用流程的完整性和健壮性。记住一定加上注解：@EnableCircuitBreaker才能开启熔断器功能
    
    看下这段代码：
    resttemplate可以通过微服务的id，请求对应的服务接口，如果引入feign将会更加简单，这个下文中会有阐述。
        @Override
        @HystrixCommand(fallbackMethod = "dataAnalyzeFallBack")
        public boolean dataAnalyze(UploadDataDTO uploadDataDTO) {
            return restTemplate.postForObject("http://BUSINESS-DATA-ANALYZE/",uploadDataDTO,boolean.class,new HashMap<String,String>());
        }
     熔断器的实现，通过@HystrixCommand(fallbackMethod = "dataAnalyzeFallBack")指定。我们可以进行测试，将BUSINESS-DATA-ANALYZE服务
     暂停了，可以看到这里将会产生日志。
        @Override
        public boolean dataAnalyzeFallBack(UploadDataDTO uploadDataDTO) {
            log.info("进入 dataAnalyzeFallBack ");
            return false;
        }