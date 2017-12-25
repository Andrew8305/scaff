###scaff-zipkin 

Spring Cloud Sleuth 是为了解决在众多微服务互相调用，数据链路十分混乱而出现的。而在这里我们集成了
zipkin。


1.需要引入的ｐｏｍ有：
            
            //同样，我们会common-utils中引入通用的springcloudsleuth依赖
            <dependency>
                <groupId>scaff</groupId>
                <artifactId>common-utils</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            <!--<dependency>-->
            <!--<groupId>io.zipkin.java</groupId>-->
            <!--<artifactId>zipkin-server</artifactId>-->
            <!--</dependency>-->
            //前端页面的也来
            <dependency>
                <groupId>io.zipkin.java</groupId>
                <artifactId>zipkin-autoconfigure-ui</artifactId>
            </dependency>
            //通过rabbitmq消费数据
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-sleuth-zipkin-stream</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
            </dependency>
            <dependency>
                <groupId>io.zipkin.java</groupId>
                <artifactId>zipkin</artifactId>
                <version>1.28.1</version>
            </dependency>
            //通过elasticsearch进行数据存储
            <dependency>
                <groupId>io.zipkin.java</groupId>
                <artifactId>zipkin-autoconfigure-storage-elasticsearch-http</artifactId>
                <version>1.28.1</version>
            </dependency>

2.项目特性：
    
    默认sleuth的存储方式是内存，通过InMemoryStorage来进行的存储，另外扩展的存储方式有：
    ElasticsearchHttpStorage,MySQLStorage.这里我使用的是elasticsearch.
    
    
    
    
    
    
    
参考文章：http://blog.csdn.net/forezp/article/details/76795269 