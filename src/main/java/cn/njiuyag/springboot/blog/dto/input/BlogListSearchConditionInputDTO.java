package cn.njiuyag.springboot.blog.dto.input;

import javax.validation.constraints.NotNull;

/**
 * @author hjx
 * @date 2020/12/31
 */
public class BlogListSearchConditionInputDTO {
    @NotNull
    private Integer pageNum;
    /**
     * 页面大小
     */
    @NotNull
    private Integer pageSize;

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

    @Override
    public String toString() {
        return "BlogListSearchConditionInputDTO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
