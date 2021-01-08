package cn.njiuyag.springboot.blog.controller.admin;

import cn.njiuyag.springboot.blog.constant.ConfigConsts;
import cn.njiuyag.springboot.blog.constant.PageConsts;
import cn.njiuyag.springboot.blog.generate.mapper.AdminUserMapper;
import cn.njiuyag.springboot.blog.interceptor.NeedLogin;
import cn.njiuyag.springboot.blog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author hjx
 * @date 2020/12/25
 */
@Controller
@NeedLogin
public class IndexController {
    private final CategoryService categoryService;

    private final AdminUserMapper adminUserMapper;


    public IndexController(CategoryService categoryService, AdminUserMapper adminUserMapper) {
        this.categoryService = categoryService;
        this.adminUserMapper = adminUserMapper;
    }

    @GetMapping({ "/admin", PageConsts.INDEX_PAGE})
    public String index(Model model, HttpSession httpSession) {
        Integer loginUserId = (Integer) httpSession.getAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        model.addAttribute("path", "index");
        Long totalCategoriesCount = categoryService.countTotalCategories(loginUserId);
        model.addAttribute("categoryCount", Optional.ofNullable(totalCategoriesCount).orElse(0L));
        model.addAttribute("blogCount", 0L);
        model.addAttribute("linkCount", 0L);
        model.addAttribute("tagCount", 0L);
        model.addAttribute("commentCount", 0L);
        return "admin/index";
    }
}
