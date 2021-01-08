package cn.njiuyag.springboot.blog.service;

import cn.njiuyag.springboot.blog.dto.input.UserInfoUpdateInputDTO;
import cn.njiuyag.springboot.blog.framework.ErrorCode;
import cn.njiuyag.springboot.blog.framework.Result;
import cn.njiuyag.springboot.blog.generate.mapper.AdminUserMapper;
import cn.njiuyag.springboot.blog.generate.model.AdminUser;
import cn.njiuyag.springboot.blog.generate.model.AdminUserExample;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

/**
 * @author hjx
 * @date 2020/12/25
 */
@Service
public class UserService {
    private final AdminUserMapper adminUserMapper;

    public UserService(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    public Result<?> updateUserInfo(Integer userId, UserInfoUpdateInputDTO inputDTO) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(userId);
        if (adminUser == null) {
            return Result.fail(ErrorCode.USER_INFO_NOT_FOUND, "用户信息不存在");
        }

        if (!adminUser.getLoginUserName().equals(inputDTO.getLoginUserName())) {
            // 判断登录名是否已使用
            AdminUserExample adminUserExample = new AdminUserExample();
            adminUserExample.createCriteria().andLoginUserNameEqualTo(inputDTO.getLoginUserName());
            long count = adminUserMapper.countByExample(adminUserExample);
            if (count > 0) {
                return Result.fail(ErrorCode.USER_PASSWORD_ERROR, "用户名已存在");
            }
        }

        // 执行更新操作
        AdminUser updateUser = new AdminUser();
        updateUser.setAdminUserId(adminUser.getAdminUserId());
        updateUser.setLoginUserName(inputDTO.getLoginUserName());
        updateUser.setNickName(inputDTO.getNickName());
        int result = adminUserMapper.updateByPrimaryKeySelective(updateUser);
        if (result <= 0) {
            return Result.fail(ErrorCode.USER_DATABASE_OPERATION_ERROR, "更新用户名称信息失败");
        }
        return Result.success(null);
    }

}
