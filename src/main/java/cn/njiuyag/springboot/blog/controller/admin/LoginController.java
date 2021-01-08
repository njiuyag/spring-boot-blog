package cn.njiuyag.springboot.blog.controller.admin;

import cn.njiuyag.springboot.blog.constant.ConfigConsts;
import cn.njiuyag.springboot.blog.constant.PageConsts;
import cn.njiuyag.springboot.blog.constant.UserTip;
import cn.njiuyag.springboot.blog.dto.input.LoginInputDTO;
import cn.njiuyag.springboot.blog.framework.Result;
import cn.njiuyag.springboot.blog.generate.mapper.AdminUserMapper;
import cn.njiuyag.springboot.blog.generate.model.AdminUser;
import cn.njiuyag.springboot.blog.generate.model.AdminUserExample;
import cn.njiuyag.springboot.blog.service.AuthenticateService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

/**
 * @author hjx
 * @date 2020/12/31
 */
@Controller
public class LoginController {

    private final LocalValidatorFactoryBean validator;

    private final AuthenticateService authenticateService;

    private final AdminUserMapper adminUserMapper;

    public LoginController(LocalValidatorFactoryBean validator, AuthenticateService authenticateService, AdminUserMapper adminUserMapper) {
        this.validator = validator;
        this.authenticateService = authenticateService;
        this.adminUserMapper = adminUserMapper;
    }

    @GetMapping(PageConsts.LOGIN_PAGE)
    public String login() {
        return PageConsts.LOGIN_PAGE;
    }

    @PostMapping(PageConsts.LOGIN_PAGE)
    public String login(LoginInputDTO inputDTO, HttpSession httpSession) {
        Set<ConstraintViolation<LoginInputDTO>> validate = validator.validate(inputDTO);
        if (!validate.isEmpty()) {
            String errorMessage = validate.stream().map(c -> c.getMessage()).reduce((a, b) -> a + "\\n" + b).orElse("");
            httpSession.setAttribute(ConfigConsts.ERROR_MSG, errorMessage);
            return PageConsts.LOGIN_PAGE;
        }

        // 验证码校验
        String kaptchaCode = (String) httpSession.getAttribute(ConfigConsts.KAPTCHA_SESSION_KEY);
        if (!inputDTO.getVerifyCode().equals(kaptchaCode)) {
            httpSession.setAttribute(ConfigConsts.ERROR_MSG, UserTip.KAPTCHA_VERIFY_CODE_FAIL);
            return PageConsts.LOGIN_PAGE;
        }

        AdminUserExample adminUserExample = new AdminUserExample();
        adminUserExample.createCriteria().andLoginUserNameEqualTo(inputDTO.getUserName());
        List<AdminUser> adminUsers = adminUserMapper.selectByExample(adminUserExample);
        if (adminUsers.isEmpty()) {
            httpSession.setAttribute(ConfigConsts.ERROR_MSG, UserTip.USER_NAME_OR_PASSWORD_ERROR);
            return PageConsts.LOGIN_PAGE;
        }
        AdminUser adminUser = adminUsers.get(0);
        // 密码验证
        Result<?> result = authenticateService.verifyPassword(inputDTO.getPassword(), adminUser.getLoginPassword());
        if (!result.success()) {
            httpSession.setAttribute(ConfigConsts.ERROR_MSG, UserTip.USER_NAME_OR_PASSWORD_ERROR);
            return PageConsts.LOGIN_PAGE;
        }

        httpSession.setAttribute(ConfigConsts.LOGIN_USER_NAME_ATTR, adminUser.getNickName());
        httpSession.setAttribute(ConfigConsts.LOGIN_USER_ID_ATTR, adminUser.getAdminUserId());
        //session过期时间设置为7200秒 即两小时
        httpSession.setMaxInactiveInterval(60 * 60 * 2);
        return "redirect:" + PageConsts.INDEX_PAGE;
    }

    @PostMapping("/admin/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        httpSession.removeAttribute(ConfigConsts.LOGIN_USER_NAME_ATTR);
        httpSession.removeAttribute(ConfigConsts.ERROR_MSG);
        return PageConsts.LOGIN_PAGE;
    }
}
