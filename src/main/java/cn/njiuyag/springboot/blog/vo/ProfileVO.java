package cn.njiuyag.springboot.blog.vo;

/**
 * @author hjx
 * @date 2020/12/30
 */
public class ProfileVO extends AbstractVO{
    private String loginUserName;
    private String nickName;

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "ProfileVO{" +
                "loginUserName='" + loginUserName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}'+" parent："+super.toString();
    }
}
