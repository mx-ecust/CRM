package com.mx.crm.workbench.web.controller;

import com.mx.crm.settings.domain.User;
import com.mx.crm.settings.service.UserService;
import com.mx.crm.settings.service.impl.UserServiceImpl;
import com.mx.crm.utils.*;
import com.mx.crm.workbench.domain.Activity;
import com.mx.crm.workbench.service.ActivityService;
import com.mx.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");

        String path = req.getServletPath();

        if ("/workbench/activity/getUserList.do".equals(path)) {

            getUserList(req, resp);

        } else if ("/workbench/activity/save.do".equals(path)) {

            save(req,resp);

        }

    }

    private void save(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("执行市场活动添加操作");

        String id = UUIDUtil.getUUID();
        String owner = req.getParameter("owner");
        String name = req.getParameter("name");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String cost = req.getParameter("cost");
        String description = req.getParameter("description");
        //创建时间：当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人：当前登录用户
        String createBy = ((User)req.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setCost(cost);
        a.setStartDate(startDate);
        a.setOwner(owner);
        a.setName(name);
        a.setEndDate(endDate);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.save(a);

        PrintJson.printJsonFlag(resp, flag);

    }

    private void getUserList(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("取得用户信息列表");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(resp, uList);


    }


}
