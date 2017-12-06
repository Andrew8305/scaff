package com.scaff.swagger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "xueyunlong",name = "用户名",example = "xueyunlong",required = true)
    private String username;
    private String age;
    private String money;
}
