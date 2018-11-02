package com.house.house.service;

import com.house.house.common.bean.Comment;
import com.house.house.common.validate.BeanHelper;
import com.house.house.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/2
 * @ Description：
 * @ throws
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void addHouseComment(Long houseId, String content, Long userId) {

        addComment(houseId,null, content, userId,1);
    }

    @Transactional
    void addComment(Long houseId, Integer blogId, String content, Long userId, int type) {

        Comment comment = new Comment();
        if (type == 1) {
            comment.setHouseId(houseId);
        } else {
            comment.setBlogId(blogId);
        }
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setType(type);
        BeanHelper.onInsert(comment);
        BeanHelper.setDefaultProp(comment, Comment.class);

        commentMapper.insert(comment);
    }
}
