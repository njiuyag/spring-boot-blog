package cn.njiuyag.springboot.blog.api;

import cn.njiuyag.springboot.blog.constant.ConfigConsts;
import cn.njiuyag.springboot.blog.dto.ResultDTO;
import cn.njiuyag.springboot.blog.dto.input.UserPasswordUpdateInputDTO;
import cn.njiuyag.springboot.blog.framework.ErrorCode;
import cn.njiuyag.springboot.blog.framework.Result;
import cn.njiuyag.springboot.blog.interceptor.NeedLogin;
import cn.njiuyag.springboot.blog.service.AuthenticateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author hjx
 * @date 2020/12/31
 */
@RestController
@NeedLogin
@RequestMapping("/api/account")
public class AccountApiController {
    private final AuthenticateService authenticateService;

    public AccountApiController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PutMapping("/password")
    public ResultDTO<?> updatePassword(@Valid UserPasswordUpdateInputDTO inputDTO, HttpSession httpSession) {
        Integer loginUserId = (Integer) httpSession.getAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        Result<?> result = authenticateService.updatePassword(loginUserId, inputDTO);
        if (!result.success()) {
            return new ResultDTO<>(result.getErrorCode(), "用户密码修改失败，请联系管理员");
        }
        httpSession.removeAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        httpSession.removeAttribute(ConfigConsts.LOGIN_USER_NAME_ATTR);
        httpSession.removeAttribute(ConfigConsts.ERROR_MSG);
        return new ResultDTO<>(null);
    }


}
