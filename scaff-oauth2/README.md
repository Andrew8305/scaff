###scaff-oauth2 整合oauth2认证
基于oauth2实现的认证中心

１．需要引入的ｐｏｍ有：


2.认证逻辑：

    1，用户想要访问一个资源。
    2，需要从api-gateway进入，api-gateway会向oauth2认证中心获取一个AcessToken，
    3.用户通过获取到acesstoken访问api-gateway，调用其他资源服务
    4，资源服务在被调用的时候，会通过acesstoken去oauth2认证中心check acesstoken的有效性。