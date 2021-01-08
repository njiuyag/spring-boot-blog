package cn.njiuyag.springboot.blog.service;

import cn.hutool.crypto.SecureUtil;
import cn.njiuyag.springboot.blog.dto.input.UserPasswordUpdateInputDTO;
import cn.njiuyag.springboot.blog.framework.ErrorCode;
import cn.njiuyag.springboot.blog.framework.Result;
import cn.njiuyag.springboot.blog.generate.mapper.AdminUserMapper;
import cn.njiuyag.springboot.blog.generate.model.AdminUser;
import cn.njiuyag.springboot.blog.generate.model.AdminUserExample;
import org.springframework.stereotype.Service;

/**
 * @author hjx
 * @date 2020/12/30
 */
@Service
public class AuthenticateService {
    private final AdminUserMapper adminUserMapper;

    public AuthenticateService(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    public Result<?> verifyPassword(final String inputPassword,final String password){
        String encryptPassword = encryptPassword(inputPassword);
        if (!encryptPassword.equals(password)) {
            return Result.fail(ErrorCode.USER_PASSWORD_ERROR, "密码不匹配");
        }
        return Result.success(null);
    }

    public Result<?> updatePassword(final Integer userId, final UserPasswordUpdateInputDTO inputDTO) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(userId);
        if (adminUser == null) {
            return Result.fail(ErrorCode.USER_INFO_NOT_FOUND, "用户信息不存在");
        }

        // 原密码验证
        Result<?> result = verifyPassword(inputDTO.getOriginalPassword(), adminUser.getLoginPassword());
        if (!result.success()) {
            return result;
        }

        // 更新用户密码
        AdminUser updateUser = new AdminUser();
        updateUser.setAdminUserId(adminUser.getAdminUserId());
        updateUser.setLoginPassword(encryptPassword(inputDTO.getNewPassword()));
        int updateResult = adminUserMapper.updateByPrimaryKeySelective(updateUser);
        if (updateResult <= 0) {
            return Result.fail(ErrorCode.USER_DATABASE_OPERATION_ERROR, "数据库更新操作失败");
        }
        return Result.success(null);
    }

    private String encryptPassword(final String password) {
        return SecureUtil.md5(password);
    }
}
