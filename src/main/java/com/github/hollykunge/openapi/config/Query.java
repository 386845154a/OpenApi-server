package com.github.hollykunge.openapi.config;



import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author hollykunge
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private int pageNo = 1;
    private int pageSize = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        //分页参数
        if (params.get(PageConstants.PAGE_NUM) != null) {
            this.pageNo = Integer.parseInt(params.get("pageNo").toString());
        }
        if (params.get(PageConstants.PAGE_LIMIT) != null) {
            this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        }
        this.remove(PageConstants.PAGE_NUM);
        this.remove(PageConstants.PAGE_LIMIT);
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}