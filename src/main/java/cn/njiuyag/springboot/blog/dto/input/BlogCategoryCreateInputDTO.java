package cn.njiuyag.springboot.blog.dto.input;

import javax.validation.constraints.NotNull;

/**
 * @author hjx
 * @date 2021/1/8
 */
public class BlogCategoryCreateInputDTO {
    @NotNull(message = "分类名称不能为空")
    private String name;
    @NotNull(message = "分类图标不能为空")
    private String icon;

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

    @Override
    public String toString() {
        return "BlogCategoryCreateInputDTO{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
