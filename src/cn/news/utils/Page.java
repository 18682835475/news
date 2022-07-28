package cn.news.utils;

import java.util.List;

/**
 * 分页工具类
 * @author LCB
 * @date 2022/7/1 11:13
 */
public class Page<T> {
    private long pageNumber;// 页码
    private long pageSize;// 每页数量
    private long count;// 总数量
    private long pageCount;// 总页数
    private List<T> data;// 数据

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
        this.pageCount =  (int)Math.ceil(1.0 * count / pageSize);
        System.out.println(this.pageCount);
    }

    public long getPageCount() {
        return pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
