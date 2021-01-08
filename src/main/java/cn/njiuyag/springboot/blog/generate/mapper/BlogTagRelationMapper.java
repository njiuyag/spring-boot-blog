package cn.njiuyag.springboot.blog.generate.mapper;

import cn.njiuyag.springboot.blog.generate.model.BlogTagRelation;
import cn.njiuyag.springboot.blog.generate.model.BlogTagRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogTagRelationMapper {
    long countByExample(BlogTagRelationExample example);

    int deleteByExample(BlogTagRelationExample example);

    int deleteByPrimaryKey(Long relationId);

    int insert(BlogTagRelation record);

    int insertSelective(BlogTagRelation record);

    List<BlogTagRelation> selectByExample(BlogTagRelationExample example);

    BlogTagRelation selectByPrimaryKey(Long relationId);

    int updateByExampleSelective(@Param("record") BlogTagRelation record, @Param("example") BlogTagRelationExample example);

    int updateByExample(@Param("record") BlogTagRelation record, @Param("example") BlogTagRelationExample example);

    int updateByPrimaryKeySelective(BlogTagRelation record);

    int updateByPrimaryKey(BlogTagRelation record);
}