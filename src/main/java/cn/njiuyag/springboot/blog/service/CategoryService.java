package cn.njiuyag.springboot.blog.service;

import cn.hutool.core.bean.BeanUtil;
import cn.njiuyag.springboot.blog.dto.ResultDTO;
import cn.njiuyag.springboot.blog.dto.input.BlogCategoryCreateInputDTO;
import cn.njiuyag.springboot.blog.dto.input.BlogCategoryUpdateInputDTO;
import cn.njiuyag.springboot.blog.framework.ErrorCode;
import cn.njiuyag.springboot.blog.framework.Result;
import cn.njiuyag.springboot.blog.generate.mapper.BlogCategoryMapper;
import cn.njiuyag.springboot.blog.generate.model.BlogCategory;
import cn.njiuyag.springboot.blog.generate.model.BlogCategoryExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hjx
 * @date 2020/12/29
 */
@Service
public class CategoryService {
    private final static Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final BlogCategoryMapper categoryMapper;

    public CategoryService(@Autowired BlogCategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public Long countTotalCategories(Integer belongUserId){
        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.createCriteria()
                .andBelongUserIdEqualTo(belongUserId);
        return categoryMapper.countByExample(blogCategoryExample);
    }

    public Result<?> create(BlogCategoryCreateInputDTO inputDTO,Integer belongUserId) {
        if (existsCategoryName(belongUserId, inputDTO.getName())){
            return Result.fail(ErrorCode.CATEGORY_NAME_REPEAT, "分类名称重复");
        }

        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setName(inputDTO.getName());
        blogCategory.setIcon(inputDTO.getIcon());
        blogCategory.setBelongUserId(belongUserId);
        int i = categoryMapper.insertSelective(blogCategory);
        if (i > 0) {
            return Result.success(null);
        }else{
            return Result.fail(ErrorCode.CATEGORY_ERROR, "数据库插入操作失败");
        }
    }

    public Result<?> update(BlogCategoryUpdateInputDTO inputDTO,Integer belongUserId) {
        if (existsCategoryName(belongUserId, inputDTO.getName())){
            logger.info("分类名称重复");
            return Result.fail(ErrorCode.CATEGORY_NAME_REPEAT, "分类名称重复");
        }
        BlogCategory blogCategory = BeanUtil.copyProperties(inputDTO, BlogCategory.class);
        int i = categoryMapper.updateByPrimaryKey(blogCategory);
        if (i > 0) {
            return new Result<>(ErrorCode.SUCCESS, "分类更新成功");
        }else{
            return new Result<>(ErrorCode.CATEGORY_ERROR, "数据库执行更新操作失败");
        }
    }

    private boolean existsCategoryName(Integer belongUserId, String name) {
        BlogCategoryExample blogCategoryExample = new BlogCategoryExample();
        blogCategoryExample.createCriteria()
                .andBelongUserIdEqualTo(belongUserId)
                .andNameEqualTo(name);
        long l = categoryMapper.countByExample(blogCategoryExample);
        return l > 0;
    }

}
