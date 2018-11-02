package com.house.house.mapper;

import com.house.house.common.bean.Blog;
import com.house.house.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper extends com.github.abel533.mapper.Mapper<Blog> {

    List<Blog> selectBlog(@Param("blog") Blog blog, @Param("pageParams") PageParams pageParams);

    Long selectBlogCount(Blog blog);
}
