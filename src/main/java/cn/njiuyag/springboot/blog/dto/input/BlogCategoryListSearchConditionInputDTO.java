package cn.njiuyag.springboot.blog.dto.input;

import javax.validation.constraints.NotNull;

/**
 * @author hjx
 * @date 2021/1/8
 */
public class BlogCategoryListSearchConditionInputDTO {
    @NotNull
    private Integer pageNum;
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
        return "BlogCategoryListSearchConditionInputDTO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
