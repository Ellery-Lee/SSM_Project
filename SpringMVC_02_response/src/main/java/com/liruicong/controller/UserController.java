package com.liruicong.controller;

import com.liruicong.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 返回字符串
     * @param model
     * @return
     */
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString方法执行了...");
        //模拟从数据库中查询出User对象
        User user = new User();
        user.setUsername("美美");
        user.setPassword("123");
        user.setAge(30);
        //model对象
        model.addAttribute("user", user);
        return "success";
    }

    /**
     * 返回void
     * 请求转发：一次请求，不用编写项目的名称
     * 重定向：多次请求，需要编写项目名称，而且WEB-INF里面的内容不可以直接在URL请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testVoid方法执行了...");
//        return "success";
        //编写程序转发的程序
//        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
        //重定向
//        response.sendRedirect(request.getContextPath() + "/index.jsp");

        //设置中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //直接会进行相应
        response.getWriter().println("你好");
        return;
    }
}
