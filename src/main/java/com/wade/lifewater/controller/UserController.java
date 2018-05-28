package com.wade.lifewater.controller;

import com.alibaba.druid.util.StringUtils;
import com.wade.lifewater.model.User;
import com.wade.lifewater.service.UserService;
import com.wade.lifewater.utils.VerifyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 切换验证码
     * @param request
     * @param response
     */
    @RequestMapping("/getValicode")
    public void Valicode(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            //logger.error("获取验证码失败>>>>   ", e);
        }
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param valiCode
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(String userName, String password, String valiCode, HttpSession session, Model model){
        //获得正确的验证码的值
        String picCode = (String) session.getAttribute(VerifyUtil.RANDOMCODEKEY);
        if(!StringUtils.equalsIgnoreCase(valiCode, picCode)){
            model.addAttribute("tip", "验证码错误");
            return "page/index";
        }
        Map<String, String> map = new HashMap<String,String>();
        map.put("userName", userName);
        map.put("password", password);
        //根据用户名和密码来查询用户
        User user = userService.selectUserByNameAndPassword(map);
        if(user == null){
            model.addAttribute("tip", "密码和用户名错误");
            return "redirect:/";
        }
        session.setAttribute("user", user);
        //如果是:后面没有/在同一个Controller中重定向
        //如果是:后面有/是在不同的Controller中重定向
        return "page/index";
    }


}


