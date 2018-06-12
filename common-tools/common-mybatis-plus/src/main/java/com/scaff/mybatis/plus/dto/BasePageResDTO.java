package com.scaff.mybatis.plus.dto;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

/**
 * 基本分页响应参数
 * @author yangxvhao
 * @date 18-3-28.
 */
@Data
public class BasePageResDTO<T extends BasePageReqDTO> extends BasePageReqDTO {
    /**
     * 总条数
     */
    protected Integer total;

    /**
     * 总页数
     */
    protected Integer pageSize;

    /**
     * 设置返回 当前页 和 每页数
     * @param page 请求参数
     * @return
     */
    public T init(Page page){
        this.setCurrentPage(page.getCurrent());
        this.setStep(page.getSize());
        this.setTotal(page.getTotal());
        this.setPageSize(page.getPages());
        return (T) this;
    }
}
