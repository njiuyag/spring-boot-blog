package cn.njiuyag.springboot.blog.api;

import cn.hutool.core.bean.BeanUtil;
import cn.njiuyag.springboot.blog.constant.ConfigConsts;
import cn.njiuyag.springboot.blog.dto.PageResultDTO;
import cn.njiuyag.springboot.blog.dto.ResultDTO;
import cn.njiuyag.springboot.blog.dto.input.BlogCategoryCreateInputDTO;
import cn.njiuyag.springboot.blog.dto.input.BlogCategoryListSearchConditionInputDTO;
import cn.njiuyag.springboot.blog.dto.input.BlogCategoryUpdateInputDTO;
import cn.njiuyag.springboot.blog.dto.output.BlogCategoryListItemOutputDTO;
import cn.njiuyag.springboot.blog.framework.ErrorCode;
import cn.njiuyag.springboot.blog.framework.Result;
import cn.njiuyag.springboot.blog.generate.mapper.BlogCategoryMapper;
import cn.njiuyag.springboot.blog.generate.model.BlogCategory;
import cn.njiuyag.springboot.blog.generate.model.BlogCategoryExample;
import cn.njiuyag.springboot.blog.interceptor.NeedLogin;
import cn.njiuyag.springboot.blog.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjx
 * @date 2021/1/8
 */
@RestController
@RequestMapping("/api/category")
@NeedLogin
public class BlogCategoryApiController {
    private final CategoryService categoryService;
    private final BlogCategoryMapper blogCategoryMapper;

    public BlogCategoryApiController(CategoryService categoryService, BlogCategoryMapper blogCategoryMapper) {
        this.categoryService = categoryService;
        this.blogCategoryMapper = blogCategoryMapper;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultDTO<?> list(@Valid BlogCategoryListSearchConditionInputDTO inputDTO, HttpSession httpSession) {
        Integer loginUserId = (Integer) httpSession.getAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.createCriteria()
                .andDeletedEqualTo(false)
                .andBelongUserIdEqualTo(loginUserId);
        Page<BlogCategory> blogCategories = PageHelper.startPage(inputDTO.getPageNum(), inputDTO.getPageSize())
                .doSelectPage(() -> blogCategoryMapper.selectByExample(blogCategoryExample));
        List<BlogCategoryListItemOutputDTO> result = blogCategories.stream()
                .map(c -> BeanUtil.copyProperties(c, BlogCategoryListItemOutputDTO.class))
                .collect(Collectors.toList());
        PageResultDTO<BlogCategoryListItemOutputDTO> pageResult = new PageResultDTO<>(blogCategories.getPageNum(), blogCategories.getPageSize(),
                blogCategories.getTotal(), result);
        return new ResultDTO<>(pageResult);
    }

    @PostMapping(value = "")
    public ResultDTO<?> create(@Valid BlogCategoryCreateInputDTO inputDTO, HttpSession httpSession) {
        Integer loginUserId = (Integer) httpSession.getAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        Result<?> result = categoryService.create(inputDTO, loginUserId);
        if (result.success()) {
            return new ResultDTO<>(result.getErrorCode(), "分类创建成功");
        } else {
            if (result.getErrorCode().equals(ErrorCode.CATEGORY_NAME_REPEAT)) {
                return new ResultDTO<>(result.getErrorCode(), "分类名称已存在，请重新输入");
            } else {
                return new ResultDTO<>(result.getErrorCode(), "未知错误，请联系管理员");
            }
        }
    }

    @PutMapping(value = "")
    public ResultDTO<?> update(@Valid BlogCategoryUpdateInputDTO inputDTO, HttpSession httpSession) {
        Integer loginUserId = (Integer) httpSession.getAttribute(ConfigConsts.LOGIN_USER_ID_ATTR);
        Result<?> result = categoryService.update(inputDTO, loginUserId);
        if (result.success()) {
            return new ResultDTO<>(result.getErrorCode(), "分类更新成功");
        } else {
            if (result.getErrorCode().equals(ErrorCode.CATEGORY_NAME_REPEAT)) {
                return new ResultDTO<>(result.getErrorCode(), "分类名称已存在，请重新输入");
            } else {
                return new ResultDTO<>(result.getErrorCode(), "未知错误，请联系管理员");
            }
        }
    }

    @DeleteMapping("")
    public ResultDTO<?> delete(@RequestBody Integer[] ids) {
        if (ids.length <= 0) {
            return new ResultDTO<>(ErrorCode.PARAMS_INVALID, "参数校验不通过");
        }

        BlogCategoryExample example = new BlogCategoryExample();
        example.createCriteria()
                .andIdIn(Arrays.asList(ids));

        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setDeleted(true);
        int i = blogCategoryMapper.updateByExampleSelective(blogCategory, example);
        if (i < ids.length) {
            return new ResultDTO<>(ErrorCode.CATEGORY_ERROR, "未知错误，请联系管理员");
        }else{
            return new ResultDTO<>(ErrorCode.SUCCESS, "分类删除成功");
        }

    }
}
