package com.github.hollykunge.openapi.vo.res.base;

/**
 * 列表返回类型
 *
 * @author 协同设计小组
 * @create 2017-06-09 7:32
 */
public class ListRestResponse<T> extends BaseResponse {
    String msg;

    ListData<T> result;


    public ListData<T> getResult() {
        return result;
    }

    public ListRestResponse(String msg, int count, T data){
        this.setMessage(msg);
        this.msg = msg;
        this.result = new ListData<>(data,count);
    }

    class ListData<T>{
        private T data;
        private int count;
        public ListData(T data,int count){
            this.data = data;
            this.count = count;
        }

        public T getData() {
            return data;
        }

        public int getCount() {
            return count;
        }
    }
}
