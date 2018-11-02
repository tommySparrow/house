package com.house.house.service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.house.house.common.bean.Blog;
import com.house.house.common.page.PageData;
import com.house.house.common.page.PageParams;
import com.house.house.mapper.BlogMapper;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/2
 * @ Description：
 * @ throws
 */
@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public PageData<Blog> queryBlog(Blog blog, PageParams pageParams) {

        //获取最新的评论
        List<Blog> blogList = blogMapper.selectBlog(blog, pageParams);
        populate(blogList);
        Long count = blogMapper.selectBlogCount(blog);
        PageData<Blog> blogPageData = PageData.buildPage(blogList, count, pageParams.getPageSize(), pageParams.getPageNum());
        return blogPageData;
    }

    private void populate(List<Blog> blogs) {
        if (!blogs.isEmpty()) {
            blogs.stream().forEach(item -> {
                String stripped =  Jsoup.parse(item.getContent()).text();
                item.setDigest(stripped.substring(0, Math.min(stripped.length(),40)));
                String tags = item.getTags();
                item.getTagList().addAll(Lists.newArrayList(Splitter.on(",").split(tags)));
            });
        }
    }
}
