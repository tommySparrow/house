package com.house.house.web.controller;

import com.house.house.common.bean.User;
import com.house.house.service.CommentService;
import com.house.house.web.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/2
 * @ Description：评论
 * @ throws
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/comment/leaveComment")
    public String leaveComment(String content , Long houseId , ModelMap modelMap){

        User user = UserContext.getUser();
        Long userId = user.getId();
        commentService.addHouseComment(houseId,content,userId);

        return "redirect:/house/detail?id=" + houseId;
    }
}
