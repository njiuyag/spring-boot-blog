package cn.njiuyag.springboot.blog.controller.admin;

import cn.njiuyag.springboot.blog.constant.ConfigConsts;
import cn.njiuyag.springboot.blog.constant.PageConsts;
import cn.njiuyag.springboot.blog.generate.mapper.AdminUserMapper;
import cn.njiuyag.springboot.blog.generate.model.AdminUser;
import cn.njiuyag.springboot.blog.vo.ProfileVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hjx
 * @date 2020/12/31
 */
@Controller
public class UserController {

    private final AdminUserMapper adminUserMapper;

    public UserController(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    @GetMapping(PageConsts.PROFILE_PAGE)
    public String profile(HttpServletRequest request, Model model) {
        Integer userId = (Integer) request.getSession().getAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(userId);
        if (adminUser == null) {
            return PageConsts.LOGIN_PAGE;
        }

        ProfileVO profileVO = new ProfileVO();
        profileVO.setNickName(adminUser.getNickName());
        profileVO.setLoginUserName(adminUser.getLoginUserName());
        model.addAttribute("profile", profileVO);
        return PageConsts.PROFILE_PAGE;
    }
}
