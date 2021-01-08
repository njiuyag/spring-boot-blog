package cn.njiuyag.springboot.blog.dto.output;

import java.util.Date;

/**
 * @author hjx
 * @date 2021/1/8
 */
public class BlogCategoryListItemOutputDTO {
    private Integer id;

    private String name;

    private String icon;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BlogCategoryListItemOutputDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
