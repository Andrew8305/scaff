###business-swagger swagger 模块，示例swagger的使用


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
        <dependency>
            <groupId>scaff</groupId>
            <artifactId>scaff-business-model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
````
2.常用注解：代码详见SwaggerTestController

    1.@Api(value = "swagger test",description = "this is a description",produces = MediaType.APPLICATION_JSON_VALUE)
    作用在类上，申明该类的一些基础信息,produces表明这次请求数据的格式。tags类的标签，
    2.@ApiOperation(value = "数据管理平台上传数据接口"),作用在方法上，申明一个接口的基础信息，
    3.@ApiImplicitParams({
                  @ApiImplicitParam(name = "username",value = "用户姓名",required = false, dataType = "String",paramType = "body")
          })
    作用在方法上，申明这个方法的参数的意义。
    4.public boolean uploadData(@ModelAttribute @FastJson UploadDataDTO uploadDataDTO)
    通过@ModelAttribute 注解，可以让springfox能够识别UploadDataDTO这个对象，去解析这个类里边字段的属性，
    5.@ApiModel(value = "上传数据",description = "上传数据")
          public class UploadDataDTO {
              @ApiModelProperty(value = "xueyunlong",name = "用户名",example = "xueyunlong")
              private String username;
              private String age;
              private String money;
          }
    @ApiModel注解标识这个类可以被识别
    6.@ApiModelProperty(value = "xueyunlong",name = "用户名",example = "xueyunlong")
    作用到字段上，描述这个字段的基础信息

    
   