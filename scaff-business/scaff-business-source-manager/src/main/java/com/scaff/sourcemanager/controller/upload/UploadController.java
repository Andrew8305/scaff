package com.scaff.sourcemanager.controller.upload;

import com.scaff.common.web.argsresolver.FastJson;
import com.scaff.enums.swagger.ParamType;
import com.scaff.sourcemanager.controller.upload.dto.UploadDataDTO;
import com.scaff.sourcemanager.controller.upload.service.UploadFeignService;
import com.scaff.sourcemanager.controller.upload.service.UploadService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.datatype.DatatypeConstants;

/**
 * Created by xyl on 11/9/17.
 * 上传资源
 */
@Slf4j
@RestController
@Api(value = "数据管理平台上传",tags = {"api"},produces = "application/json")
public class UploadController {

    @Autowired
    UploadService uploadService;
    @Autowired
    UploadFeignService uploadFeignService;

    @ApiOperation(value = "数据管理平台上传数据接口")
    @RequestMapping(value="/data",method= RequestMethod.POST)
    public boolean uploadData(@FastJson UploadDataDTO uploadDataDTO){
        System.out.println(uploadDataDTO.toString());
        /*
        * 调用数据分析平台，分析数据
        * */
        return uploadFeignService.dataAnalyze(uploadDataDTO.toUser());
//        return uploadService.dataAnalyze(uploadDataDTO.toUser());
    }

    @RequestMapping(value = "/test/{username}",method = RequestMethod.GET)
    @ApiOperation(value = "测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户姓名",required = false, dataType = "String",paramType = "body")
    })
    public boolean test(@PathVariable(value = "username") String username) throws InterruptedException {
        System.out.println(username);
        Thread.sleep(10000l);
        return false;
    }

}
