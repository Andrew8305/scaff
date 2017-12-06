package com.scaff.sourcemanager.controller.upload.dto;

import com.scaff.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xyl on 11/9/17.
 */
@Data@Builder@AllArgsConstructor@NoArgsConstructor
@ApiModel(value = "上传数据",description = "上传数据")
public class UploadDataDTO {
    @ApiModelProperty(value = "xueyunlong",name = "用户名",example = "xueyunlong")
    private String username;
    private String age;
    private String money;
    public User toUser(){
        return User.builder()
                .username(username)
                .age(age)
                .money(money)
                .build();
    }
}
