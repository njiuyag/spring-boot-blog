package cn.njiuyag.springboot.blog.dto.input;

import javax.validation.constraints.NotBlank;

/**
 * @author hjx
 * @date 2020/12/31
 */
public class UserInfoUpdateInputDTO {
    @NotBlank
    private String loginUserName;
    @NotBlank
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
        return "UserInfoUpdateInputDTO{" +
                "loginUserName='" + loginUserName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
