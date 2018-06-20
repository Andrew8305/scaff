###scaff 
项目基于JDK1.8，springboot-1.5.9.RELEASE ,springcloud-Dalston.RELEASE完成

项目特性：

```
    1.模块划分清晰：抽取通用模块，业务模块
    2.代码规范
    3.项目结构清晰
    4.代码简洁：自定义注解提高开发效率
```
能够运行的模块：

```
    1.scaff-api-gateway
    2.scaff-discovery-center-eureka
    3.scaff-zipkin
    4.scaff-business:data-analyze,source-manager,swagger
```
温馨提示：
```
    1.欢迎star
    2.欢迎提交代码
    3.有一定的代码洁癖，请忍耐
    4.每个模块都会有详细文档说明，详看对应目录README.md
    最后，感谢陈老师的辛苦栽培
```

## 模块介绍：

#### 1. [common-tool](https://github.com/xueyunlong123/scaff/tree/master/common-tools)
#### 1.1 说明
<pre>
    本模块主要整合了通用的一些工具集。
   【distribute-lock】基于redis实现的分布式锁。可以通过注解，快速的在项目的任何地方添加分布式
      锁，锁的key通过注解传入的参数确定
   【mongo】基于morphia实现的mongo模块，该模块自动注入了mongoconfig配置类，以及datastore类。
      你可以在任何引入该模块，并注入Datastore来进行你的mongo操作。当然，你需要在对应模块的配置
      文件添加你的mongo配置
   【mybatis-plus】mybatis的升级版，国人通过mybtis的封装，使其使用更加方便快捷。我在其中也自己
      定义了web请求的分页dto。你可以通过集成这些基础的dto，来完成你的分页查询。同样，在generator包，
      你可以看到，你可以快速的通过你数据库的表生成resource资源文件夹中的模板类。
   【redis】基于redisson实现的redis模块，自动注入redisconfig以及redis配置。其他模块可以直接
      引用该模块，自动注入redissonClient来进行你的redis操作
</pre>
 