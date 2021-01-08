package cn.njiuyag.springboot.blog.controller.admin;

import cn.njiuyag.springboot.blog.interceptor.NeedLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hjx
 * @date 2021/1/8
 */
@Controller
@RequestMapping("/admin")
@NeedLogin
public class CategoryController {

    @RequestMapping("/category/list")
    public String list(Model model) {
        model.addAttribute("path", "categories");
        return "admin/category";
    }
}
