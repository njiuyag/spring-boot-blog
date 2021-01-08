package cn.njiuyag.springboot.blog.api;

import cn.njiuyag.springboot.blog.constant.ConfigConsts;
import cn.njiuyag.springboot.blog.dto.ResultDTO;
import cn.njiuyag.springboot.blog.dto.input.UserInfoUpdateInputDTO;
import cn.njiuyag.springboot.blog.framework.Result;
import cn.njiuyag.springboot.blog.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author hjx
 * @date 2020/12/31
 */
@RestController("apiUserController")
@RequestMapping("/api/user")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/name")
    public ResultDTO<?> updateNameInfo(@Valid UserInfoUpdateInputDTO inputDTO, HttpSession httpSession) {
        Integer loginUserId = (Integer) httpSession.getAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        Result<?> result = userService.updateUserInfo(loginUserId, inputDTO);
        if (!result.success()) {
            return new ResultDTO<>(result.getErrorCode(), "更新用户名称操作失败，请联系管理员处理");
        }
        return new ResultDTO<>(null);
    }
}
