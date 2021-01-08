package cn.njiuyag.springboot.blog.vo;

/**
 * @author hjx
 * @date 2020/12/30
 */
public abstract class AbstractVO {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
