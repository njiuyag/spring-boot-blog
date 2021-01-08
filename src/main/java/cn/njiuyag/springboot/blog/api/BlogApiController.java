package cn.njiuyag.springboot.blog.api;

import cn.hutool.core.bean.BeanUtil;
import cn.njiuyag.springboot.blog.dto.ResultDTO;
import cn.njiuyag.springboot.blog.dto.input.BlogListSearchConditionInputDTO;
import cn.njiuyag.springboot.blog.dto.PageResultDTO;
import cn.njiuyag.springboot.blog.dto.output.BlogListItemOutputDTO;
import cn.njiuyag.springboot.blog.generate.mapper.BlogMapper;
import cn.njiuyag.springboot.blog.generate.model.Blog;
import cn.njiuyag.springboot.blog.generate.model.BlogExample;
import cn.njiuyag.springboot.blog.interceptor.NeedLogin;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjx
 * @date 2020/12/31
 */
@RestController
@RequestMapping("/api")
@NeedLogin
public class BlogApiController {
    private final BlogMapper blogMapper;

    public BlogApiController(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @GetMapping("/blog/list")
    public ResultDTO<?> list(@Valid BlogListSearchConditionInputDTO inputDTO) {
        BlogExample blogExample = new BlogExample();
        Page<Blog> blogList = PageHelper.startPage(inputDTO.getPageNum(), inputDTO.getPageSize())
                .doSelectPage(() -> blogMapper.selectByExample(blogExample));

        List<BlogListItemOutputDTO> result = blogList.stream().map(c -> BeanUtil.copyProperties(c, BlogListItemOutputDTO.class))
                .collect(Collectors.toList());

        PageResultDTO<BlogListItemOutputDTO> pageResultDTO =
                new PageResultDTO<>(blogList.getPageNum(), blogList.getPageSize(), blogList.getTotal(), result);
        return new ResultDTO<>(pageResultDTO);
    }
}
