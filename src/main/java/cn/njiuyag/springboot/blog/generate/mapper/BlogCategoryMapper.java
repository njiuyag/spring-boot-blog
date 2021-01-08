package cn.njiuyag.springboot.blog.generate.mapper;

import cn.njiuyag.springboot.blog.generate.model.BlogCategory;
import cn.njiuyag.springboot.blog.generate.model.BlogCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogCategoryMapper {
    long countByExample(BlogCategoryExample example);

    int deleteByExample(BlogCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    List<BlogCategory> selectByExample(BlogCategoryExample example);

    BlogCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    int updateByExample(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);
}