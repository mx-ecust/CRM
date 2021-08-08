package com.mx.crm.settings.web.controller;

import com.mx.crm.settings.domain.User;
import com.mx.crm.settings.service.UserService;
import com.mx.crm.settings.service.impl.UserServiceImpl;
import com.mx.crm.utils.MD5Util;
import com.mx.crm.utils.PrintJson;
import com.mx.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入到用户控制器");

        String path = req.getServletPath();

        if ("/settings/user/login.do".equals(path)) {

            login(req, resp);

        } else if ("/settings/user/xxx.do".equals(path)) {

            //xxx(request,response);

        }

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("进入到验证登录操作");

        String loginAct = req.getParameter("loginAct");
        String loginPwd = req.getParameter("loginPwd");
        //将密码的明文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收浏览器端的ip地址
        String ip = req.getRemoteAddr();
        System.out.println("--------------ip:" + ip);

        //未来业务层开发，统一使用代理类形态的接口对象
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try {

            User user = us.login(loginAct, loginPwd, ip);

            req.getSession().setAttribute("user", user);

            //如果程序执行到此处，说明业务层没有为controller抛出任何的异常
            //表示登录成功
            /*

                {"success":true}

             */
            PrintJson.printJsonFlag(resp, true);

        } catch (Exception e) {
            e.printStackTrace();

            //一旦程序执行了catch块的信息，说明业务层为我们验证登录失败，为controller抛出了异常
            //表示登录失败
            /*

                {"success":true,"msg":?}

             */
            String msg = e.getMessage();
            /*

                我们现在作为contoller，需要为ajax请求提供多项信息

                可以有两种手段来处理：
                    （1）将多项信息打包成为map，将map解析为json串
                    （2）创建一个Vo
                            private boolean success;
                            private String msg;


                    如果对于展现的信息将来还会大量的使用，我们创建一个vo类，使用方便
                    如果对于展现的信息只有在这个需求中能够使用，我们使用map就可以了


             */
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            map.put("msg", msg);
            PrintJson.printJsonObj(resp, map);

        }
    }
}
