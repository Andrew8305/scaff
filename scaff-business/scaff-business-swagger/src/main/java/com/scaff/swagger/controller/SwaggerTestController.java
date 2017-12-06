package com.scaff.swagger.controller;

import com.scaff.common.web.argsresolver.FastJson;
import com.scaff.swagger.dto.UploadDataDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xyl on 11/15/17.
 */
@RestController
@Api(value = "swagger test",description = "this is a description",produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerTestController {

    @ApiOperation(value = "数据管理平台上传数据接口")
    @RequestMapping(value="/data",method= RequestMethod.POST)
    public boolean uploadData(@ModelAttribute @FastJson UploadDataDTO uploadDataDTO){
        System.out.println(uploadDataDTO.toString());
        /*
        * 调用数据分析平台，分析数据
        * */
        return false;
//        return uploadService.dataAnalyze(uploadDataDTO.toUser());
    }

    @RequestMapping(value = "/test/{username}",method = RequestMethod.GET)
    @ApiOperation(value = "测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户姓名",required = true, dataType = "String",paramType = "body")
    })
    public boolean test(@PathVariable(value = "username") String username){
        System.out.println(username);
        return false;
    }

}
