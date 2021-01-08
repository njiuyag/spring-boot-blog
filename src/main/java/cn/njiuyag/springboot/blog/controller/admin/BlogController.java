package cn.njiuyag.springboot.blog.controller.admin;

import cn.njiuyag.springboot.blog.constant.PageConsts;
import cn.njiuyag.springboot.blog.interceptor.NeedLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hjx
 * @date 2020/12/31
 */
@Controller
@NeedLogin
public class BlogController {

    @GetMapping(PageConsts.BLOG_LIST_PAGE)
    public String list(Model model) {
        model.addAttribute("path", "blogs");
        return PageConsts.BLOG_LIST_PAGE;
    }

    @GetMapping("/admin/blog/create")
    public String createBlog(Model model) {
        model.addAttribute("path", "edit");
        model.addAttribute("categories", null);
        return "admin/edit";
    }
}
