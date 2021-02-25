package com.github.hollykunge.openapi.vo.res.base;

import lombok.Data;

import java.util.List;

/**
 * 分页结果返回
 *
 * @author 协同设计小组
 * @create 2017-06-14 22:40
 */
@Data
public class TableResultResponse<T> extends BaseResponse {

    TableData<T> result;

    public TableResultResponse(int pageSize, int pageNo, int totalPage, long totalCount, List<T> data) {
        this.result = new TableData<T>(data,pageSize,pageNo,totalPage,totalCount);
    }

    public TableResultResponse() {
        this.result = new TableData<T>();
    }

    class TableData<T> {
        private List<T> data;
        private int pageSize;
        private int pageNo;
        private int totalPage;
        private long totalCount;

        public TableData(List<T> data,int pageSize,int pageNo,int totalPage,long totalCount) {
            this.data = data;
            this.pageSize = pageSize;
            this.pageNo = pageNo;
            this.totalPage = totalPage;
            this.totalCount = totalCount;
        }

        public TableData(){

        }

        public List<T> getData() {
            return data;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getPageNo() {
            return pageNo;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public long getTotalCount() {
            return totalCount;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "result=" + result +
                "}";
    }
}
