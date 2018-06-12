package com.scaff.mybatis.plus.dto;

import lombok.Data;

/**
 * 基本分页请求参数
 * @author yangxvhao
 * @date 18-3-28.
 */
@Data
public class BasePageReqDTO {
        /**
         * 访问第几页
         */
        protected Integer currentPage;

        /**
         * 每页显示多少条
         */
        protected Integer step;

        public Integer getCurrentPage() {
            return currentPage == null || currentPage <= 0 ? 1 : currentPage;
        }

        public Integer getStep() {
            return step == null || step <= 0 ? 10 : step > 50 ? 50 : step;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public void setStep(Integer step) {
            this.step = step;
        }
}
