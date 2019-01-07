package com.daniel.controller;

import com.daniel.common.Result;
import com.daniel.common.ResultGenerator;
import com.daniel.pojo.BookCollect;
import com.daniel.pojo.User;
import com.daniel.service.BookCollectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/collect")
public class BookCollectController {

//    private static final Logger LOGGER = Logger.getLogger(BookCollectController.class);

    @Autowired
    private BookCollectService bookCollectService;

    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public Result add(HttpServletRequest request, int bookId) {

        // 获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");

        BookCollect bookCollect = new BookCollect();
        bookCollect.setBookId(bookId);
        bookCollect.setStudentId(user.getStudentid());
        bookCollectService.add(bookCollect);
        return ResultGenerator.genSuccessResult(null);
    }

}
