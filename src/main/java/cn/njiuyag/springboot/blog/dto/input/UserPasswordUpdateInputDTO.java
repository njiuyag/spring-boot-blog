package cn.njiuyag.springboot.blog.dto.input;

import javax.validation.constraints.NotBlank;

/**
 * @author hjx
 * @date 2020/12/31
 */
public class UserPasswordUpdateInputDTO {
    @NotBlank
    private String originalPassword;

    @NotBlank
    private String newPassword;


    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UserPasswordUpdateInputDTO{" +
                ", originalPassword='" + originalPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
