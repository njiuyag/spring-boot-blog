package cn.njiuyag.springboot.blog.dto;

import java.util.List;

/**
 * @author hjx
 * @date 2021/1/4
 */
public class PageResultDTO<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer pages;
    private Long total;
    private List<T> list;

    public PageResultDTO(Integer pageNum, Integer pageSize, Long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        this.pages= (int) Math.ceil((double) total / pageSize);
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResultDTO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                '}';
    }

    public Integer getPages() {
        return pages;
    }
}
