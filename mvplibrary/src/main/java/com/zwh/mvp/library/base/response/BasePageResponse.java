package com.zwh.mvp.library.base.response;

import java.util.List;

/**
 * @author Zhaohao
 * @Description: 带分页的数据请求返回
 * @Date 2018/08/20 11:08
 */

public class BasePageResponse<T> extends Response{

    private PageReslut<T> data;

    public PageReslut<T> getData() {
        return data;
    }

    public void setData(PageReslut<T> data) {
        this.data = data;
    }

    public static class PageReslut<T> {
        private int total;
        private List<T> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<T> getRows() {
            return rows;
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
        }


    }
}
