package com.daniel.controller;

import com.daniel.common.Result;
import com.daniel.common.ResultGenerator;
import com.daniel.pojo.Book;
import com.daniel.pojo.BookCollect;
import com.daniel.pojo.User;
import com.daniel.service.BookCollectService;
import com.daniel.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookCollectService bookCollectService;

    // 日志文件
    private static final Logger log = Logger.getLogger(UserController.class);

    @RequestMapping("")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 验证登录
     * @param user 用户输入的学号与密码封装成的User对象
     * @param request 登录成功时将user存入session当中
     * @return 登录成功后跳转至首页
     */
    @RequestMapping(value = "/sessions",method = RequestMethod.POST)
    @ResponseBody
    public Result checkLogin(@RequestBody User user, HttpServletRequest request) {
        // userService验证是否登录成功
        boolean flag = userService.checkUser(user);
        log.info("request: user/login , user: " + user.toString());
        if (flag) {
            Map data = new HashMap();
            data.put("currentUser",user);
            // 登录成功，将登录信息放入session
             request.getSession().setAttribute("user",userService.getByStudentid(user.getStudentid()));
            return ResultGenerator.genSuccessResult(data);
        }else {
            return ResultGenerator.genFailResult("学号或密码输入错误！");
        }
    }

    /**
     * 登出操作
     * @param request 用于获取session中的User对象
     * @return 登出后跳转至登录界面
     */
    @RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new ModelAndView("login");
    }

    /***
     * 跳转到个人主页
     * @return
     */
    @RequestMapping("/myhome/{id}")
    public ModelAndView myhome(@PathVariable("id") String id ,HttpServletRequest request) {
        ModelAndView mav =new ModelAndView("myhome");
        // 获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        int intId = Integer.parseInt(id);
        Map<Integer, List<Book>> collectMap = bookCollectService.getBookAndCollectBook(intId,user.getStudentid());
        List<Integer> pageCount = bookCollectService.getPageCount(user.getStudentid());
        mav.addObject("pageCount",pageCount);
        mav.addObject("collectMap",collectMap);
        mav.addObject("intId",intId);
        return mav;
    }


    /**
     * 修改个人信息
     * @param request
     * @param tel 电话
     * @param address 地址
     * @param major 专业
     * @return
     */
    @RequestMapping(value = "/change-info",method = RequestMethod.POST)
    @ResponseBody
    public Result changeinfo(HttpServletRequest request, String tel, String address, String major) {

        if(!StringUtils.hasText(tel)){
            return ResultGenerator.genFailResult("电话号码不能为空");
        }
        if(!StringUtils.hasText(address)){
            return ResultGenerator.genFailResult("地址不能为空");
        }
        if(!StringUtils.hasText(major)){
            return ResultGenerator.genFailResult("班级不能为空");
        }
        if(address.length() > 8){
            return ResultGenerator.genFailResult("地址太长，请重新输入");
        }

        // 获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        user.setAddress(address);
        user.setTel(tel);
        user.setMajor(major);
        userService.updateUserInfo(user);
        return ResultGenerator.genSuccessResult(null);
    }

}
