package com.zwh.mvp.api;

import java.util.List;

/**
 * @author Zhaohao
 * @Description: 带分页的结果返回
 * @date 2018-11-19 15:31
 */
public class BackPageResponse<T> {


    /**
     * code : 100
     * data : {"current":1,"pages":601,"records":[{"account":"17700586035","createTime":"2018-11-15 20:17:05","email":"","fromType":0,"id":602,"lastTime":"2018-11-15 20:17:05","money":0,"name":"胡斌","openId":"","operatorId":1,"phone":"13100978555","pwd":"123456","score":0,"sex":0,"shopId":1,"status":1,"totalScore":0,"type":0,"valid":1}],"size":1,"total":601}
     * msg : 获取成功
     */

    private int code;
    private PageResult<T> data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageResult getData() {
        return data;
    }

    public void setData(PageResult data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class PageResult<T> {
        /**
         * current : 1
         * pages : 601
         * records : []
         * size : 1
         * total : 601
         */

        private int current; //当前页码
        private int pages; //总页数
        private int size;//每页条数
        private int total;//总条数
        private List<T> records;//数据集合

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<T> getRecords() {
            return records;
        }

        public void setRecords(List<T> records) {
            this.records = records;
        }


    }
}
