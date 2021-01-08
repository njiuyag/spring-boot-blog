package cn.njiuyag.springboot.blog.dto.output;

import java.util.Date;

/**
 * @author hjx
 * @date 2020/12/31
 */
public class BlogListItemOutputDTO {
    private Long blogId;
    private String blogTitle;
    private String blogCoverImage;
    private String blogCategoryName;
    private Byte blogStatus;
    private Long blogViews;
    private Date createTime;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogCoverImage() {
        return blogCoverImage;
    }

    public void setBlogCoverImage(String blogCoverImage) {
        this.blogCoverImage = blogCoverImage;
    }

    public String getBlogCategoryName() {
        return blogCategoryName;
    }

    public void setBlogCategoryName(String blogCategoryName) {
        this.blogCategoryName = blogCategoryName;
    }

    public Byte getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Byte blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Long getBlogViews() {
        return blogViews;
    }

    public void setBlogViews(Long blogViews) {
        this.blogViews = blogViews;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BlogListItemOutputDTO{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogCoverImage='" + blogCoverImage + '\'' +
                ", blogCategoryName='" + blogCategoryName + '\'' +
                ", blogStatus=" + blogStatus +
                ", blogViews=" + blogViews +
                ", createTime=" + createTime +
                '}';
    }
}
