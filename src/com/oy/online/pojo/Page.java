package com.oy.online.pojo;

import java.util.List;
/**
*@Description
*@Author OY
*@Date 2020/8/16
*@Time 16:26
*/
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    // 当前页码
    private Integer pageNO;
    // 总页码
    private Integer pageTotal;
    // 当前页显示数量
    private Integer pageSize = PAGE_SIZE;
    // 总记录数
    private Integer pageTotalCount;
    // 当前页数据
    private List<T> items;

    // 分页条的请求地址
    private String url;

    public Page() {
    }

    public Page(Integer pageNO, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
        this.pageNO = pageNO;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }


    public Integer getPageNO() {
        return pageNO;
    }

    public void setPageNO(Integer pageNO) {

        /**
         * 边界值检查
         */
        if(pageNO < 1){
            pageNO = 1;
        }
        if(pageNO > pageTotal){
            pageNO = pageTotal;
        }
        this.pageNO = pageNO;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNO=" + pageNO +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
